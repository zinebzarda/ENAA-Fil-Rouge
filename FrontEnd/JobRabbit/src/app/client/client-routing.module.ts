import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientComponent } from './client.component';
import {RegisterClientComponent} from "./register-client/register-client.component";
import {EditClientComponent} from "./edit-client/edit-client.component";
import {ListClientComponent} from "./list-client/list-client.component";
import {ListServicesComponent} from "../service/list-services/list-services.component";
import {HomeComponent} from "../Homes/home/home.component";

const routes: Routes = [
  { path: '', component: ClientComponent ,children:[
  {path: 'listService' ,component:ListServicesComponent},
  { path: '', redirectTo: 'listService', pathMatch: 'full' },
  { path: 'register', component: RegisterClientComponent },
  { path: 'editClient', component: EditClientComponent },
  { path: 'listClient', component: ListClientComponent }

    ] },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientRoutingModule { }
