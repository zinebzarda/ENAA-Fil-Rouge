import {Component, Input, OnInit} from '@angular/core';
import {ServicesService} from "../core/services/services.service";
import {Router} from "@angular/router";
import {AuthService} from "../core/services/auth.service";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit{

  @Input() userRole!: string | null;

  constructor(
    private  authService:AuthService
  ) {}


  ngOnInit(): void {
    this.userRole =this.authService.getRoleFROMjWT()
    console.log("the role is :", this.authService.getRoleFROMjWT())
  }


}
