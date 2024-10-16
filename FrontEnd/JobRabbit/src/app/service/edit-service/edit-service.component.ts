import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Services } from '../../models/services';
import { ServicesService } from '../../core/services/services.service';
import { UploadImageServiceService } from '../../core/services/upload-image-service.service';

@Component({
  selector: 'app-edit-service',
  templateUrl: './edit-service.component.html',
  styleUrls: ['./edit-service.component.css']
})
export class EditServiceComponent implements OnInit {
  editServiceForm: FormGroup;
  serviceId!: number;
  imageUrl: string | null = null;
  selectedImageFile: string | null = null;

  constructor(
    private fb: FormBuilder,
    private serviceService: ServicesService,
    private route: ActivatedRoute,
    private router: Router,
    private uploadImageService: UploadImageServiceService
  ) {
    this.editServiceForm = this.fb.group({
      titre: ['', Validators.required],
      description: ['', Validators.required],
      prix: [0, [Validators.required, Validators.min(1)]],
      image: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.serviceId = this.route.snapshot.params['id'];

    // Récupérer le service par ID et pré-remplir le formulaire
    this.serviceService.getServiceById(this.serviceId).subscribe(service => {
      this.editServiceForm.patchValue(service);
      this.imageUrl = service.image; // Afficher l'image existante
    });
  }

  async onSubmit(){
    if (this.editServiceForm.valid) {
      const updatedService: Services = this.editServiceForm.value;

      if (this.selectedImageFile) {
        updatedService.image = await this.uploadImageService.uploadImageToCloudinary(this.selectedImageFile);
      }

      this.serviceService.updateService(this.serviceId, updatedService).subscribe({
        next: () => {
          alert('Service modifié avec succès');
          this.router.navigate(['/prestataire/listService']);
        },
        error: err => {
          console.error('Erreur lors de la modification du service', err);
        }
      });
    } else {
      alert('Veuillez remplir tous les champs requis');
    }
  }

  onFileSelected(event: any, controlName: string): void {
    const file = event.target.files[0];
    if (file && file.type.match(/image\/.*/) != null) {
      const previewUrl = URL.createObjectURL(file);
      this.editServiceForm.patchValue({
        [controlName]: file
      });
      this.editServiceForm.get(controlName)?.markAsTouched();
      this.imageUrl = previewUrl;
      this.selectedImageFile = file;
    } else {
      alert('Veuillez sélectionner une image valide');
    }
  }
}
