import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Review} from "../../models/review.model";


@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  private baseUrl = 'http://localhost:8080/reviews';

  constructor(private http: HttpClient) { }


  getReviews(): Observable<Review[]> {
    return this.http.get<Review[]>(`${this.baseUrl}`);
  }

  getReviewsByClient(clientId: number): Observable<Review[]> {
    return this.http.get<Review[]>(`${this.baseUrl}/client/${clientId}`);
  }


  createReview(review: Review): Observable<Review> {
    return this.http.post<Review>(`${this.baseUrl}`, review);
  }
}
