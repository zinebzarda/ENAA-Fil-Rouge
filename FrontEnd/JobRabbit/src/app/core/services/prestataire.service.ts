// prestataire.service.ts
import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import {Prestataire} from "../../models/prestataire";
import {Client} from "../../models/client";

@Injectable({
  providedIn: 'root'
})
export class PrestataireService {
  private apiUrl = 'http://localhost:8080/prestataires';

  constructor(private http: HttpClient) { }

  private getToken(): string | null {
    return localStorage.getItem('authToken'); // Retrieve JWT token
  }

  private getHeaders(): HttpHeaders {
    const token = this.getToken();
    return new HttpHeaders({
      'Authorization': `Bearer ${token}` // Add JWT token to headers
    });
  }

  createPrestataire(prestataire: Prestataire): Observable<Prestataire> {
    return this.http.post<Prestataire>(`${this.apiUrl}/inscription`, prestataire);
  }

  getAllPrestataires(): Observable<Prestataire[]> {
    return this.http.get<Prestataire[]>(`${this.apiUrl}/allPrestataires`);
  }

  getPrestataireById(id: number): Observable<Prestataire> {
    return this.http.get<Prestataire>(`${this.apiUrl}/${id}`);
  }


  updatePrestataire(prestataire: Prestataire): Observable<Client> {
    return this.http.put<Client>(`${this.apiUrl}/update/${prestataire.id}`, prestataire, { headers: this.getHeaders() });
  }


  deletePrestataire(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${id}`);
  }

  getPendingPrestataires(): Observable<Prestataire[]> {
    return this.http.get<Prestataire[]>(`${this.apiUrl}/pending`);
  }

  verifyPrestataire(id: number, status: string): Observable<Prestataire> {
    return this.http.post<Prestataire>(`${this.apiUrl}/verify/${id}`, {}, { params: { status } });
  }
}
