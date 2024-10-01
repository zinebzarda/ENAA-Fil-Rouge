
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Services} from "../models/services";

@Injectable({
  providedIn: 'root'
})
export class ServicesService {
  private apiUrl = 'http://localhost:9092/Services';
  constructor(private http: HttpClient) {}

  // Créer un service
  createService(service:Services): Observable<Services> {
    return this.http.post<Services>(this.apiUrl, service);
  }

  // Récupérer tous les services
  getAllServices(): Observable<Services[]> {
    return this.http.get<Services[]>(`${this.apiUrl}/all`);
  }

  // Récupérer un service par ID
  getServiceById(id: number): Observable<Services> {
    return this.http.get<Services>(`${this.apiUrl}/${id}`);
  }

  // Mettre à jour un service
  updateService(id: number, serviceDetails: Services): Observable<Services> {
    return this.http.put<Services>(`${this.apiUrl}/update/${id}`, serviceDetails);
  }

  // Supprimer un service
  deleteService(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${id}`);
  }

  // Rechercher des services par titre
  searchServices(titre: string): Observable<Services[]> {
    return this.http.get<Services[]>(`${this.apiUrl}/search`, { params: { titre } });
  }
}
