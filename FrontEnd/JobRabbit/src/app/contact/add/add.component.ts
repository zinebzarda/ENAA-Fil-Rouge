import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {ContactService} from "../../core/services/contact.service";
import {Contact} from "../../models/contact";

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent {
  contactForm: FormGroup;

  constructor(private fb: FormBuilder, private contactService: ContactService) {
    this.contactForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phone: ['', Validators.required],
      message: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.contactForm.valid) {
      const newContact: Contact = this.contactForm.value;
      this.contactService.createContact(newContact).subscribe(
        (response) => {
          console.log('Message envoyé avec succès', response);
        },
        (error) => {
          console.error('Erreur lors de l\'envoi du message', error);
        }
      );
    } else {
      console.log('Formulaire invalide');
    }
  }
  showAlert(){
    alert(" Message envoyé avec succès")
  }
}
