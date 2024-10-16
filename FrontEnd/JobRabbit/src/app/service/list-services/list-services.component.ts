// list-services.component.ts
import { Component, Input, OnInit } from '@angular/core';
import { Services } from '../../models/services';
import { Router } from '@angular/router';
import { ServicesService } from "../../core/services/services.service";
import { PageEvent } from "@angular/material/paginator";
import { AuthService } from "../../core/services/auth.service";

@Component({
  selector: 'app-list-services',
  templateUrl: './list-services.component.html',
  styleUrls: ['./list-services.component.css']
})
export class ListServicesComponent implements OnInit {
  services: Services[] = [];
  paginatedServices: Services[] = [];
  pageSize: number = 20;
  pageIndex: number = 0;
  searchTerm: string = '';
  @Input() userRole!: string | null;
  isAuthenticated: boolean = false;

  constructor(
    private serviceService: ServicesService,
    private router: Router,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.isAuthenticated = this.authService.isAuthenticated();
    if (this.isAuthenticated) {
      this.userRole = this.authService.getRoleFROMjWT();
      this.loadServices();
    } else {
      console.error("User is not authenticated");
      this.router.navigate(['/login']);
    }
    console.log("The role is:", this.userRole);
  }

  loadServices(): void {
    console.log("Current user role:", this.userRole);
    if (this.userRole === 'PRESTATAIRE') {
      const id = this.authService.getUserIdFromToken();
      console.log("Prestataire ID:", id);
      if (id) {
        this.serviceService.getAllServicesByPrestataireId(id).subscribe({
          next: (services: Services[]) => {
            this.services = services;
            this.updatePaginatedServices();
            console.log("Services loaded for prestataire:", services);
          },
          error: (error) => {
            console.error('Error fetching services for prestataire:', error);
            if (error.status === 404) {
              console.log("No services found for this prestataire");
            } else if (error.status === 401) {
              console.log("Unauthorized access. Redirecting to login...");
              this.router.navigate(['/login']);
            }
          }
        });
      } else {
        console.error('Prestataire ID is missing.');
      }
    } else if (this.userRole === 'ADMIN' || this.userRole === 'CLIENT') {
      this.serviceService.getAllServices().subscribe({
        next: (services: Services[]) => {
          this.services = services;
          this.updatePaginatedServices();
          console.log("All services loaded:", services);
        },
        error: (error) => {
          console.error('Error fetching all services:', error);
        }
      });
    } else {
      console.error("Unknown user role:", this.userRole);
    }
  }

  editService(id: number | undefined): void {
    if (id !== undefined) {
      this.router.navigate(['/prestataire/editService', id]);
    } else {
      console.error("Cannot edit service: ID is undefined");
    }
  }

  deleteService(id: number | undefined): void {
    if (id === undefined) {
      console.error("Cannot delete service: ID is undefined");
      return;
    }

    if (confirm('Êtes-vous sûr de vouloir supprimer ce service ?')) {
      this.serviceService.deleteService(id).subscribe({
        next: () => {
          this.loadServices();
          this.showAlert('Service supprimé avec succès');
        },
        error: (err) => {
          console.error('Erreur lors de la suppression du service', err);
          this.showAlert('Erreur lors de la suppression du service');
        }
      });
    }
  }

  updatePaginatedServices(): void {
    const startIndex = this.pageIndex * this.pageSize;
    this.paginatedServices = this.filterServices(this.services).slice(startIndex, startIndex + this.pageSize);
  }

  onPageChange(event: PageEvent): void {
    this.pageIndex = event.pageIndex;
    this.pageSize = event.pageSize;
    this.updatePaginatedServices();
  }

  showAlert(message: string) {
    alert(message);
  }

  login() {
    this.router.navigate(['/login']);
  }

  // Search functionality: filtering only by title
  filterServices(services: Services[]): Services[] {
    if (!this.searchTerm) {
      return services;
    }
    return services.filter(service =>
      service.titre.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }

  onSearchTermChange() {
    this.pageIndex = 0;
    this.updatePaginatedServices();
  }
}
