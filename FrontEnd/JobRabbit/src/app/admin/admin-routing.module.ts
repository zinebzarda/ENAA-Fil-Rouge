import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin.component';
import {ListContactComponent} from "../contact/list-contact/list-contact.component";
import {ListPrestataireComponent} from "../prestataire/list-prestataire/list-prestataire.component";
import {ListClientComponent} from "../client/list-client/list-client.component";
import {ListServicesComponent} from "../service/list-services/list-services.component";
import {EditServiceComponent} from "../service/edit-service/edit-service.component";


const routes: Routes = [
  { path: '', component: AdminComponent , children: [
      { path: 'listContact', component: ListContactComponent },
      { path: '', redirectTo: 'listContact', pathMatch: 'full' },
      { path: 'listPrestataire', component: ListPrestataireComponent },
      { path: 'listClient', component: ListClientComponent },
      { path:'listService',component:ListServicesComponent},
      {path: 'editService',component:EditServiceComponent}


    ]},

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
