import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {RegisterPraistataireComponent} from "./register-praistataire/register-praistataire.component";
import {ListServicesComponent} from "../service/list-services/list-services.component";
import {EditServiceComponent} from "../service/edit-service/edit-service.component";
import {AddServiceComponent} from "../service/add-service/add-service.component";
import {PrestataireComponent} from "./prestataire.component";

const routes: Routes = [
  { path: '', component: PrestataireComponent , children: [
    { path: 'register', component:RegisterPraistataireComponent},
    { path:'addService',component:AddServiceComponent},
    { path: '', redirectTo: 'addService', pathMatch: 'full' },
    { path:'listService',component:ListServicesComponent},
    { path: 'editService',component:EditServiceComponent}
    ]},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PrestataireRoutingModule { }
