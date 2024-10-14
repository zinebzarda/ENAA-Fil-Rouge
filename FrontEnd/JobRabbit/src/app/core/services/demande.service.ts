import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DemandeService } from '../../models/demande-service';

@Injectable({
  providedIn: 'root'
})
export class DemandeServiceService {
  private apiUrl = 'http://localhost:8080/demandes';

  constructor(private http: HttpClient) {}

  addDemande(demande: DemandeService, serviceId: number): Observable<DemandeService> {
    return this.http.post<DemandeService>(`${this.apiUrl}/add/${serviceId}`, demande);
  }

  getDemandes(): Observable<DemandeService[]> {
    return this.http.get<DemandeService[]>(`${this.apiUrl}`);
  }

  getDemandesByServiceId(serviceId: number): Observable<DemandeService[]> {
    return this.http.get<DemandeService[]>(`${this.apiUrl}/service/${serviceId}`);
  }

  getDemandeById(id: number): Observable<DemandeService> {
    return this.http.get<DemandeService>(`${this.apiUrl}/${id}`);
  }

  deleteDemande(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${id}`);
  }
}
