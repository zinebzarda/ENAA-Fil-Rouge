import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';
import { ClientModule } from "../client/client.module";
import { PrestataireModule } from "../prestataire/prestataire.module";


@NgModule({
  declarations: [
    AdminComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    ClientModule,
    PrestataireModule,
  ]
})
export class AdminModule { }
