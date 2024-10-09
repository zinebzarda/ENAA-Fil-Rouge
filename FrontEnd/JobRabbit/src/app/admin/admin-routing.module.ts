import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin.component';
import {ListContactComponent} from "../contact/list-contact/list-contact.component";

const routes: Routes = [
  { path: '', component: AdminComponent , children: [
      { path: 'listContact', component: ListContactComponent }
    ]},

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
