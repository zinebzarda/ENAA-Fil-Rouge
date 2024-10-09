import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Prestataire} from "../../models/prestataire";
import {PrestataireService} from "../../core/services/prestataire.service";
import {Client} from "../../models/client";
import {ClientService} from "../../core/services/client.service";

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent {

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
