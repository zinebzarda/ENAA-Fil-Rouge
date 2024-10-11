import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {JwtHelperService} from "@auth0/angular-jwt";


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/auth/connexion';

  constructor(private http: HttpClient,
              private jwtHelper:JwtHelperService
              ) { }

  login(username: string, password: string): Observable<any> {
    return this.http.post<any>(this.apiUrl, { username, password });
  }

  isAuthenticated(): boolean {
    return !!localStorage.getItem('token');
  }

  getRole(): string | null {
    const token = localStorage.getItem('token');
    if (token) {
      const payload = JSON.parse(atob(token.split('.')[1]));
      return payload.roles;
    }
    return null;
  }

  logout(): void {
    localStorage.removeItem('token');
  }



  getRoleFROMjWT():string{
    const token = localStorage.getItem('token');
    if(token)
    {
      const decodedToken = this.jwtHelper.decodeToken(token);
      return decodedToken.roles;
    }
    return '';
  }


}
