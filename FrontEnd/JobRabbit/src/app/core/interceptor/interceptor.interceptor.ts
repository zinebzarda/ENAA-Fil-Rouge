import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse
} from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';

@Injectable()
export class Interceptor implements HttpInterceptor {

  constructor(private authService: AuthService, private router: Router) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    // Retrieve the token from the AuthService
    const token = localStorage.getItem('token');

    // If token exists, clone the request and set the Authorization header
    if (token) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      });
    }

    // Handle the request and catch errors
    return next.handle(request).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 401) {
          // If the error is a 401 Unauthorized, log the user out and redirect to login
          this.authService.logout();
          this.router.navigate(['/login'], { queryParams: { sessionExpired: true } });
        } else if (error.status === 403) {
          // Handle Forbidden errors
          console.error('Access forbidden: ', error.message);
        }

        // Throw the error to be handled by the component
        return throwError(() => new Error(error.message || 'Server error'));
      })
    );
  }
}
