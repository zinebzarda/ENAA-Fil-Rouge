import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DemandeService } from '../models/demande-service';

@Injectable({
  providedIn: 'root'
})
export class DemandeServiceService {
  private apiUrl = 'http://localhost:8080/demandes';

  constructor(private http: HttpClient) {}

  createDemandeService(clientId: number, serviceId: number): Observable<DemandeService> {
    return this.http.post<DemandeService>(`${this.apiUrl}?clientId=${clientId}&serviceId=${serviceId}`, {});
  }

  getAllDemandes(): Observable<DemandeService[]> {
    return this.http.get<DemandeService[]>(this.apiUrl);
  }

  getDemandeById(id: number): Observable<DemandeService> {
    return this.http.get<DemandeService>(`${this.apiUrl}/${id}`);
  }

  updateDemande(id: number, clientId?: number, serviceId?: number, statut?: string): Observable<DemandeService> {
    // Crée un objet HttpParams pour inclure uniquement les paramètres définis
    let params = new HttpParams();

    if (clientId) {
      params = params.set('clientId', clientId.toString());
    }
    if (serviceId) {
      params = params.set('serviceId', serviceId.toString());
    }
    if (statut) {
      params = params.set('statut', statut);
    }

    // Envoie la requête PUT avec les paramètres
    return this.http.put<DemandeService>(`${this.apiUrl}/update/${id}`, {}, { params });
  }

  deleteDemande(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${id}`);
  }
}
