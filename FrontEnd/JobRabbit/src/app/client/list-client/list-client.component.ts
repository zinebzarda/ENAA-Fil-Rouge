import { Component, OnInit } from '@angular/core';
import { Client } from "../../models/client";
import { ClientService } from "../../core/services/client.service";
import {PageEvent} from "@angular/material/paginator";

@Component({
  selector: 'app-list-client',
  templateUrl: './list-client.component.html',
  styleUrls: ['./list-client.component.css']
})
export class ListClientComponent implements OnInit {

  clients: Client[] = [];
  paginatedClients: Client[] = [];
  pageSize: number = 10;
  pageIndex: number = 0;

  constructor(private clientService: ClientService) { }

  ngOnInit(): void {
    this.loadClients();
  }

  loadClients(): void {
    this.clientService.getAllClients().subscribe(
      (data: Client[]) => {
        this.clients = data;
        console.log('Loaded clients:', this.clients);
        this.updatePaginatedClients(); // Mettre à jour les clients paginés
      },
      (error) => {
        console.error('Error fetching clients', error);
      }
    );
  }

  updatePaginatedClients(): void {
    const startIndex = this.pageIndex * this.pageSize;
    this.paginatedClients = this.clients.slice(startIndex, startIndex + this.pageSize);
  }

  onPageChange(event: PageEvent): void {
    this.pageIndex = event.pageIndex;
    this.pageSize = event.pageSize;
    this.updatePaginatedClients(); // Mettre à jour la pagination
  }

  deleteClient(id: number | undefined): void {
    if (id) {
      this.clientService.deleteClient(id).subscribe(() => {
        this.loadClients(); // Recharger les clients après suppression
      });
    } else {
      console.error('Client ID is undefined');
    }
  }

  // openEditPopup(client: Client): void {
  //   this.selectedClient = client;
  //   this.showEditPopup = true;
  // }
  //
  // closeEditPopup(): void {
  //   this.showEditPopup = false;
  //   this.selectedClient = null;
  //   this.loadClients(); // Recharger les clients après modification
  // }


  showAlert(){
    alert("Client supprimé !")
  }
}
