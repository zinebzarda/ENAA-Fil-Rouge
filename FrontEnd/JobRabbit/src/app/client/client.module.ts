import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ClientRoutingModule } from './client-routing.module';
import { ClientComponent } from './client.component';
import { RegisterClientComponent } from './register-client/register-client.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { EditComponent } from './edit/edit.component';
import { ListComponent } from './list/list.component';


@NgModule({
  declarations: [
    ClientComponent,
    RegisterClientComponent,
    EditComponent,
    ListComponent
  ],
    exports: [
        ClientComponent,
        RegisterClientComponent,
        ListComponent,
        EditComponent
    ],
  imports: [
    CommonModule,
    ClientRoutingModule,
    ReactiveFormsModule,
    FormsModule
  ]
})
export class ClientModule { }
