import { Component } from '@angular/core';
import { AuthService } from "../../core/services/auth.service";
import { Router } from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router) { }

  onSubmit() {
    this.authService.login(this.username, this.password).subscribe({
      next: (response: { token: string; }) => {
        localStorage.setItem('token', response.token);
        const role = this.authService.getRole();
        if (role === 'ADMIN') {
          this.router.navigate(['/admin']);
        } else if (role === 'PRESTATAIRE') {
          this.router.navigate(['/prestataire']);
        } else if (role === 'CLIENT') {
          this.router.navigate(['/client']);
        }
      },
      error: (errorResponse) => {
        if (errorResponse.status === 401) {
          this.errorMessage = 'Invalid username or password';
        } else if (errorResponse.status === 404) {
          this.errorMessage = 'User not registered. Please sign up first.';
        } else {
          this.errorMessage = 'An error occurred. Please try again later.';
        }
      }
    });
  }
}
