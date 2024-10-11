import { Component } from '@angular/core';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  selectedTab: string = 'client';

  selectTab(tab: string) {
    this.selectedTab = tab;
  }
}
