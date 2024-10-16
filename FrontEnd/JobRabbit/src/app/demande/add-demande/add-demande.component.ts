import { Component, OnInit } from '@angular/core';
import { DemandeService } from "../../models/demande-service";
import { DemandeServiceService } from "../../core/services/demande.service";
import { ActivatedRoute, Router } from "@angular/router";

@Component({
  selector: 'app-add-demande',
  templateUrl: './add-demande.component.html',
  styleUrls: ['./add-demande.component.css']
})
export class AddDemandeComponent implements OnInit {

  demandeService: DemandeService = {
    id: undefined,
    dateDemmande: new Date(),
    client: null,
    service: null,
    feedbacks: []
  };

  confirmationMessage: string = ''; // Add a confirmation message property

  constructor(
    private demandeServiceService: DemandeServiceService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const serviceId = Number(this.route.snapshot.paramMap.get('id'));
    this.demandeService.dateDemmande = new Date();
    this.addDemande(serviceId);
  }

  addDemande(serviceId: number): void {
    this.demandeServiceService.addDemande(this.demandeService, serviceId).subscribe({
      next: (response) => {
        console.log('Demande ajoutée avec succès', response);
        this.confirmationMessage = 'Votre demande a été ajoutée avec succès!'; // Set confirmation message
        this.router.navigate(['/book/:id']);
      },
      error: (err) => {
        console.error('Erreur lors de l\'ajout de la demande', err);
        this.confirmationMessage = 'Une erreur s\'est produite lors de l\'ajout de votre demande. Veuillez réessayer.'; // Set error message
      }
    });
  }
}
