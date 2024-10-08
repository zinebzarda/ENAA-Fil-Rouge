import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PrestataireService} from "../../core/services/prestataire.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register-praistataire',
  templateUrl: './register-praistataire.component.html',
  styleUrls: ['./register-praistataire.component.css']
})
export class RegisterPraistataireComponent implements OnInit {
  registerForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private prestataireService: PrestataireService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.registerForm = this.fb.group({
      username: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
      confirmPassword: ['', [Validators.required]],
      domaineExpertise: ['', Validators.required],
      disponibilites: ['', Validators.required],
      experience: [null, [Validators.required, Validators.pattern("^[0-9]+$")]],
      tel: [null, [Validators.required, Validators.pattern("^[0-9]+$")]]
    }, {validator: this.passwordMatchValidator});
  }

  passwordMatchValidator(formGroup: FormGroup) {
    const password = formGroup.get('password')?.value;
    const confirmPassword = formGroup.get('confirmPassword')?.value;
    if (password !== confirmPassword) {
      formGroup.get('confirmPassword')?.setErrors({ passwordMismatch: true });
    } else {
      formGroup.get('confirmPassword')?.setErrors(null);
    }
  }

  submitForm() {
    if (this.registerForm.valid) {
      const prestataireData = {
        ...this.registerForm.value,
        role: 'PRESTATAIRE',
        experience: Number(this.registerForm.get('experience')?.value), // Convert to number
        tel: Number(this.registerForm.get('tel')?.value) // Convert to number
      };
      this.prestataireService.createPrestataire(prestataireData).subscribe(
        (response) => {
          console.log('Prestataire registered:', response);
          this.router.navigateByUrl("/login");
        },
        (error) => {
          console.error('Registration error:', error);
        }
      );
    }
  }
}

