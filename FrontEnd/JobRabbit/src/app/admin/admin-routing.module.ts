import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin.component';
import {ListContactComponent} from "../contact/list-contact/list-contact.component";
import {PrestataireComponent} from "../prestataire/prestataire.component";

const routes: Routes = [
  { path: '', component: AdminComponent , children: [
      { path: 'listContact', component: ListContactComponent },
      { path: 'prestataire', component: PrestataireComponent },


    ]},

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
