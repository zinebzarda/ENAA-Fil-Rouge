import {Component, Input, OnInit} from '@angular/core';
import {AuthService} from "../core/services/auth.service";

@Component({
  selector: 'app-prestataire',
  templateUrl: './prestataire.component.html',
  styleUrls: ['./prestataire.component.css']
})
export class PrestataireComponent implements OnInit{

  @Input() userRole!: string | null;

  constructor(
    private  authService:AuthService
  ) {}


  ngOnInit(): void {
    this.userRole =this.authService.getRoleFROMjWT()
    console.log("the role is :", this.authService.getRoleFROMjWT())
  }


}
