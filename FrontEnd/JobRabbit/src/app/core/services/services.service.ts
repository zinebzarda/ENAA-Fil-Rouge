import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Services } from "../../models/services";

@Injectable({
  providedIn: 'root'
})
export class ServicesService {
  private apiUrl = 'http://localhost:8080/services';

  constructor(private http: HttpClient) {}

  // Create a service
  createService(service: Services): Observable<Services> {
    return this.http.post<Services>(`${this.apiUrl}/add`, service).pipe(
      catchError(this.handleError)
    );
  }

  // Get all services
  getAllServices(): Observable<Services[]> {
    return this.http.get<Services[]>(`${this.apiUrl}/all`).pipe(
      catchError(this.handleError)
    );
  }

  // Get a service by ID
  getServiceById(id: number): Observable<Services> {
    return this.http.get<Services>(`${this.apiUrl}/${id}`).pipe(
      catchError(this.handleError)
    );
  }

  // Update a service
  updateService(id: number, serviceDetails: Services): Observable<Services> {
    return this.http.put<Services>(`${this.apiUrl}/update/${id}`, serviceDetails).pipe(
      catchError(this.handleError)
    );
  }

  // Delete a service
  deleteService(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${id}`).pipe(
      catchError(this.handleError)
    );
  }

  // Search services by title
  searchServices(titre: string): Observable<Services[]> {
    return this.http.get<Services[]>(`${this.apiUrl}/search`, { params: { titre } }).pipe(
      catchError(this.handleError)
    );
  }

  // Get all services for a prestataire by ID
  getAllServicesByPrestataireId(id: string): Observable<Services[]> {
    return this.http.get<Services[]>(`${this.apiUrl}/prestataire/${id}`).pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse) {
    let errorMessage = 'An unknown error occurred!';
    if (error.error instanceof ErrorEvent) {
      // Client-side or network error
      errorMessage = `Error: ${error.error.message}`;
    } else {
      // Backend returned unsuccessful response code
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    console.error(errorMessage);
    return throwError(() => new Error(errorMessage));
  }
}
