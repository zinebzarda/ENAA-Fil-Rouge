import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Feedback} from "../../models/feedback";

@Injectable({
  providedIn: 'root'
})
export class FeedbackService {
  private apiUrl = 'http://localhost:8080/feedback';

  constructor(private http: HttpClient) {}

  createFeedback(demandeServiceId: number, note: number, commentaire: string): Observable<Feedback> {
    return this.http.post<Feedback>(`${this.apiUrl}/${demandeServiceId}`, {}, { params: { note, commentaire } });
  }

  getAllFeedbacks(): Observable<Feedback[]> {
    return this.http.get<Feedback[]>(this.apiUrl);
  }

  getFeedbackById(id: number): Observable<Feedback> {
    return this.http.get<Feedback>(`${this.apiUrl}/${id}`);
  }

  updateFeedback(id: number, note: number, commentaire: string): Observable<Feedback> {
    return this.http.put<Feedback>(`${this.apiUrl}/update/${id}`, {}, { params: { note, commentaire } });
  }

  deleteFeedback(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${id}`);
  }
}
