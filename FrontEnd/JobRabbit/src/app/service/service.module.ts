import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ServiceRoutingModule } from './service-routing.module';
import { ServiceComponent } from './service.component';
import { AddServiceComponent } from './add-service/add-service.component';
import { ListServicesComponent } from './list-services/list-services.component';
import { EditServiceComponent } from './edit-service/edit-service.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatPaginatorModule} from "@angular/material/paginator";


@NgModule({
    declarations: [
        ServiceComponent,
        AddServiceComponent,
        ListServicesComponent,
        EditServiceComponent
    ],
  exports: [
    AddServiceComponent,
    ListServicesComponent
  ],
  imports: [
    CommonModule,
    ServiceRoutingModule,
    ReactiveFormsModule,
    MatPaginatorModule,
    FormsModule
  ]
})
export class ServiceModule { }
