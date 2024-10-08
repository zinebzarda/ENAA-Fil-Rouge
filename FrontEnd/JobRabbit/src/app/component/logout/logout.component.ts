import {AfterViewInit, Component} from '@angular/core';
import {AuthService} from "../../core/services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements AfterViewInit{

  constructor(private authService : AuthService , private router: Router) {}

  ngAfterViewInit() : void {
    this.authService.logout()
    this.router.navigateByUrl("/login")
  }

}
