import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientComponent } from './client.component';
import {RegisterClientComponent} from "./register-client/register-client.component";

const routes: Routes = [
  { path: '', component: ClientComponent },
  { path: 'register', component: RegisterClientComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientRoutingModule { }
