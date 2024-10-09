import { Component, OnInit } from '@angular/core';
import { Client } from "../../models/client";
import { ClientService } from "../../core/services/client.service";

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  clients: Client[] = [];
  showEditPopup = false;
  selectedClient: Client | null = null;

  constructor(private clientService: ClientService) { }

  ngOnInit(): void {
    this.loadClients();
  }

  loadClients(): void {
    this.clientService.getAllClients().subscribe(
      (data: Client[]) => {
        this.clients = data;
        console.log('Loaded clients:', this.clients);
      },
      (error) => {
        console.error('Error fetching clients', error);
      }
    );
  }


  deleteClient(id: number | undefined): void {
    if (id) {
      this.clientService.deleteClient(id).subscribe(() => {
        this.loadClients();
      });
    } else {
      console.error('Client ID is undefined');
    }
  }


  openEditPopup(client: Client): void {
    this.selectedClient = client;
    this.showEditPopup = true;
  }

  closeEditPopup(): void {
    this.showEditPopup = false;
    this.selectedClient = null;
    this.loadClients();
  }
}
