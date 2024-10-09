import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Client} from "../../models/client";
import {ClientService} from "../../core/services/client.service";

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent {

  @Input() client: Client | null = null;
  @Output() closePopup = new EventEmitter<void>();
  constructor(private clientService: ClientService) {}

  onSubmit() {
    if (this.client) {
      this.clientService.updateClient(this.client).subscribe(() => {
        this.closePopup.emit();
      });
    }
  }

  closePopupMethod() {
    this.closePopup.emit();
  }
}



