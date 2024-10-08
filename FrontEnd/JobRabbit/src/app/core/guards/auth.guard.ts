import { CanActivateFn } from '@angular/router';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import {AuthService} from "../services/auth.service";

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  const role = authService.getRole();
  const expectedRole = route.data['role'] as string;

  if (authService.isAuthenticated() && role === expectedRole) {
    return true;
  } else {
    router.navigate(['/login']);
    return false;
  }
};
