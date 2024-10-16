import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Services } from '../../models/services';
import {ServicesService} from "../../core/services/services.service";
import {UploadImageServiceService} from "../../core/services/upload-image-service.service";

@Component({
  selector: 'app-add-service',
  templateUrl: './add-service.component.html',
  styleUrls: ['./add-service.component.css']
})
export class AddServiceComponent implements OnInit {
  serviceForm: FormGroup;
  imageUrl : string | null = null;

  constructor(
    private fb: FormBuilder,
    private serviceService: ServicesService,
    private router: Router,
    private uploadImageService : UploadImageServiceService
  ) {
    this.serviceForm = this.fb.group({
      titre: ['', Validators.required],
      description: ['', Validators.required],
      prix: [0, [Validators.required, Validators.min(1)]],
      image: ['', Validators.required]
    });
  }

  ngOnInit(): void {}

 async onSubmit() {
    if (this.serviceForm.valid) {
      const newService: Services = this.serviceForm.value;
newService.image = await this.uploadImageService.uploadImageToCloudinary(newService.image);
      // Appel au service pour créer un service
      this.serviceService.createService(newService).subscribe({
        next: () => {
          alert('Service ajouté avec succès');
          this.router.navigate(['/prestataire/listService']);
        },
        error: err => {
          console.error('Erreur lors de l\'ajout du service', err);
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
      this.serviceForm.patchValue({
        [controlName]: file
      });
      this.serviceForm.get(controlName)?.markAsTouched();
      this.imageUrl = previewUrl;
    }else {
      alert('Veuillez sélectionner une image valide');
    }
  }


}
