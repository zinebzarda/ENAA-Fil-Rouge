import { Component, OnInit } from '@angular/core';
import { DemandeServiceService } from "../../core/services/demande.service";
import { ServicesService } from "../../core/services/services.service"; // Importer le service des services
import { DemandeService } from "../../models/demande-service";

@Component({
  selector: 'app-list-demande',
  templateUrl: './list-demande.component.html',
  styleUrls: ['./list-demande.component.css']
})
export class ListDemandeComponent implements OnInit {
  demandes: DemandeService[] = [];
  paginatedDemandes: DemandeService[] = [];
  services: any[] = [];
  selectedServiceId: number | null = null;
  pageSize: number = 5; // Nombre d'éléments par page
  pageIndex: number = 0; // Page courante

  constructor(
    private demandeServiceService: DemandeServiceService,
    private servicesService: ServicesService
  ) {}

  ngOnInit(): void {
    this.getAllServices();
    this.getDemandes(); // Récupérer toutes les demandes au démarrage
  }

  getAllServices(): void {
    this.servicesService.getAllServices().subscribe({
      next: (data) => {
        this.services = data;
      },
      error: (err) => {
        console.error('Erreur lors de la récupération des services', err);
      }
    });
  }

  getDemandes(): void {
    this.demandeServiceService.getDemandes().subscribe({
      next: (data) => {
        this.demandes = data;
        this.updatePaginatedDemandes();
      },
      error: (err) => {
        console.error('Erreur lors de la récupération des demandes', err);
      }
    });
  }

  getDemandesByServiceId(serviceId: number): void {
    this.demandeServiceService.getDemandesByServiceId(serviceId).subscribe({
      next: (data) => {
        this.demandes = data;
        this.updatePaginatedDemandes();
      },
      error: (err) => {
        console.error('Erreur lors de la récupération des demandes', err);
      }
    });
  }

  onServiceChange(): void {
    if (this.selectedServiceId) {
      this.getDemandesByServiceId(this.selectedServiceId);
    } else {
      this.getDemandes(); // Si aucun service n'est sélectionné, récupérer toutes les demandes
    }
  }

  onPageChange(event: any): void {
    this.pageIndex = event.pageIndex;
    this.pageSize = event.pageSize;
    this.updatePaginatedDemandes();
  }

  updatePaginatedDemandes(): void {
    const startIndex = this.pageIndex * this.pageSize;
    this.paginatedDemandes = this.demandes.slice(startIndex, startIndex + this.pageSize);
  }
}
