import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ClientRoutingModule } from './client-routing.module';
import { ClientComponent } from './client.component';
import { RegisterClientComponent } from './register-client/register-client.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {EditClientComponent} from "./edit-client/edit-client.component";
import {ListClientComponent} from "./list-client/list-client.component";
import {MatPaginatorModule} from "@angular/material/paginator";
import {ServiceModule} from "../service/service.module";
import {AppModule} from "../app.module";


@NgModule({
  declarations: [
    ClientComponent,
    RegisterClientComponent,
    EditClientComponent,
    ListClientComponent
  ],
    exports: [
        ClientComponent,
        RegisterClientComponent,
      ListClientComponent,
      EditClientComponent
    ],
  imports: [
    CommonModule,
    ClientRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    MatPaginatorModule,
    ServiceModule,
  ]
})
export class ClientModule { }
