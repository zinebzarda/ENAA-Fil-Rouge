import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Client} from "../../models/client";
import {ClientService} from "../../core/services/client.service";

@Component({
  selector: 'app-edit-client',
  templateUrl: './edit-client.component.html',
  styleUrls: ['./edit-client.component.css']
})
export class EditClientComponent {

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



