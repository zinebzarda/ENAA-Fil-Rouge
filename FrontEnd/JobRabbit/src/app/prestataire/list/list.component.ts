import { Component, OnInit } from '@angular/core';
import { Prestataire } from "../../models/prestataire";
import { PrestataireService } from "../../core/services/prestataire.service";
import { PageEvent } from '@angular/material/paginator'; // Import PageEvent
import { ValidateStatus } from "../../models/enums/validate-status.enum";

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  prestataires: Prestataire[] = [];
  paginatedPrestataires: Prestataire[] = [];
  pageSize: number = 10;
  pageIndex: number = 0;
  showEditPopup = false;
  selectedPrestataire: Prestataire | null = null;

  rejectedCount: number = 0;
  pendingCount: number = 0;
  validatedCount: number = 0;
  activeCount: number = 0;
  totalPrestataires: number = 0;

  activeTab: string = 'all'; // Valeur par défaut pour afficher tous les prestataires

  constructor(private prestataireService: PrestataireService) { }

  ngOnInit(): void {
    this.loadPrestataires();
  }

  loadPrestataires(): void {
    this.prestataireService.getAllPrestataires().subscribe(
      (data: Prestataire[]) => {
        this.prestataires = data;
        this.totalPrestataires = this.prestataires.length;
        this.updateCounts();  // Compter les statuts
        this.updatePaginatedPrestataires();
      },
      (error) => {
        console.error('Error fetching prestataires', error);
      }
    );
  }

  updatePaginatedPrestataires(): void {
    const startIndex = this.pageIndex * this.pageSize;

    // Filtrer les prestataires en fonction de l'onglet actif
    let filteredPrestataires: Prestataire[];
    if (this.activeTab === 'validated') {
      filteredPrestataires = this.prestataires.filter(p => p.status === ValidateStatus.ACCEPTE);
    } else if (this.activeTab === 'pending') {
      filteredPrestataires = this.prestataires.filter(p => p.status === ValidateStatus.EN_ATTENTE);
    } else if (this.activeTab === 'rejected') {
      filteredPrestataires = this.prestataires.filter(p => p.status === ValidateStatus.REFUSE);
    } else {
      filteredPrestataires = this.prestataires; // Tous les prestataires
    }

    this.paginatedPrestataires = filteredPrestataires.slice(startIndex, startIndex + this.pageSize);
  }

  updateCounts(): void {
    this.rejectedCount = this.prestataires.filter(p => p.status === ValidateStatus.REFUSE).length;
    this.pendingCount = this.prestataires.filter(p => p.status === ValidateStatus.EN_ATTENTE).length;
    this.validatedCount = this.prestataires.filter(p => p.status === ValidateStatus.ACCEPTE).length;
  }

  onPageChange(event: PageEvent): void {
    this.pageIndex = event.pageIndex;
    this.pageSize = event.pageSize;
    this.updatePaginatedPrestataires();
  }

  deletePrestataire(id: number | undefined): void {
    if (id) {
      this.prestataireService.deletePrestataire(id).subscribe(() => {
        this.loadPrestataires();
      });
    } else {
      console.error('Prestataire ID is undefined');
    }
  }

  openEditPopup(prestataire: Prestataire): void {
    this.selectedPrestataire = prestataire;
    this.showEditPopup = true;
  }

  closeEditPopup(): void {
    this.showEditPopup = false;
    this.selectedPrestataire = null;
    this.loadPrestataires();
  }

  getStatusLabel(status: ValidateStatus): string {
    switch (status) {
      case ValidateStatus.EN_ATTENTE:
        return 'En Attente';
      case ValidateStatus.ACCEPTE:
        return 'Accepté';
      case ValidateStatus.REFUSE:
        return 'Refusé';
      default:
        return 'Statut Inconnu';
    }
  }

  showAlert(): void {
    alert("Prestataire supprimé !");
  }

  changeTab(tab: string): void {
    this.activeTab = tab;
    this.pageIndex = 0; // Réinitialiser l'index de la page à 0 lors du changement d'onglet
    this.updatePaginatedPrestataires();
  }
}
