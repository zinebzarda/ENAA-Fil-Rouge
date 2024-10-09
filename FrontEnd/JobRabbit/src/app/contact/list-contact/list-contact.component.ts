import { Component, OnInit } from '@angular/core';
import { Contact } from "../../models/contact";
import { ContactService } from "../../core/services/contact.service";
import { PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-list-contact',
  templateUrl: './list-contact.component.html',
  styleUrls: ['./list-contact.component.css']
})
export class ListContactComponent implements OnInit {

  contacts: Contact[] = [];
  paginatedContacts: Contact[] = [];
  pageSize: number = 10;
  pageIndex: number = 0;

  constructor(private contactService: ContactService) { }

  ngOnInit(): void {
    this.loadContact(); // Appeler loadContact ici
  }

  loadContact(): void {
    this.contactService.getAllContacts().subscribe(
      (data: Contact[]) => {
        this.contacts = data;
        this.updatePaginatedContacts(); // Mettez à jour la liste paginée après le chargement des contacts
        console.log('Loaded contacts:', this.contacts); // Vérifiez que les données sont chargées
      },
      (error) => {
        console.error('Error fetching contacts', error);
      }
    );
  }

  updatePaginatedContacts(): void {
    const startIndex = this.pageIndex * this.pageSize;
    this.paginatedContacts = this.contacts.slice(startIndex, startIndex + this.pageSize);
  }

  onPageChange(event: PageEvent): void {
    this.pageIndex = event.pageIndex;
    this.pageSize = event.pageSize;
    this.updatePaginatedContacts(); // Mettez à jour les contacts paginés lorsque la page change
  }

  deleteContact(id: number | undefined): void {
    if (id) {
      this.contactService.deleteContact(id).subscribe(() => {
        this.loadContact(); // Rechargez les contacts après la suppression
      });
    } else {
      console.error('Contact ID is undefined');
    }
  }

  showAlert(): void {
    alert("Message supprimé avec succès");
  }

  formatDate(date: Date | undefined): string {
    if (!date) {
      return 'N/A'; // Si la date est undefined, renvoyer une chaîne par défaut
    }
    return new Date(date).toISOString().split('T')[0]; // Convertit la date en YYYY-MM-DD
  }
}
