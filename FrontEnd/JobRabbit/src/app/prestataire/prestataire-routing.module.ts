import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PrestataireComponent } from './prestataire.component';
import {RegisterPraistataireComponent} from "./register-praistataire/register-praistataire.component";

const routes: Routes = [
  { path: 'register', component:RegisterPraistataireComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PrestataireRoutingModule { }
