import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Prestataire} from "../../models/prestataire";
import {PrestataireService} from "../../core/services/prestataire.service";

@Component({
  selector: 'app-edit-prestataire',
  templateUrl: './edit-prestataire.component.html',
  styleUrls: ['./edit-prestataire.component.css']
})
export class EditPrestataireComponent {

  @Input() prestataire: Prestataire | null = null;
  @Output() closePopup = new EventEmitter<void>();
  constructor(private prestataireService: PrestataireService) {}


  onSubmit() {
    console.log('Prestataire before update:', this.prestataire);
    if (this.prestataire) {
      this.prestataireService.updatePrestataire(this.prestataire).subscribe({
        next: () => {
          console.log('Update success');
          console.log('Updated Prestataire:', this.prestataire);  // Log mn ba3d update
          this.closePopup.emit();
        },
        error: (err) => {
          console.error('Error in update:', err);
        }
      });
    }
  }



  closePopupMethod() {
    this.closePopup.emit();
  }
}
