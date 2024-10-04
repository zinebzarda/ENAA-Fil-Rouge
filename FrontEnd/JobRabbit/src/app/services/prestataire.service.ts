// prestataire.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Prestataire} from "../models/prestataire";

@Injectable({
  providedIn: 'root'
})
export class PrestataireService {
  private apiUrl = 'http://localhost:8080/prestataires';

  constructor(private http: HttpClient) {}

  createPrestataire(prestataire: Prestataire): Observable<Prestataire> {
    return this.http.post<Prestataire>(`${this.apiUrl}/inscription`, prestataire);
  }

  getAllPrestataires(): Observable<Prestataire[]> {
    return this.http.get<Prestataire[]>(`${this.apiUrl}/allPrestataires`);
  }

  getPrestataireById(id: number): Observable<Prestataire> {
    return this.http.get<Prestataire>(`${this.apiUrl}/${id}`);
  }

  updatePrestataire(id: number, prestataire: Prestataire): Observable<Prestataire> {
    return this.http.put<Prestataire>(`${this.apiUrl}/update/${id}`, prestataire);
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
