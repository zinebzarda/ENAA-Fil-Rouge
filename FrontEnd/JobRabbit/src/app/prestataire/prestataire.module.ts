import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PrestataireRoutingModule } from './prestataire-routing.module';
import { PrestataireComponent } from './prestataire.component';
import { RegisterPraistataireComponent } from './register-praistataire/register-praistataire.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatPaginatorModule} from "@angular/material/paginator";
import {EditPrestataireComponent} from "./edit-prestataire/edit-prestataire.component";
import {ListPrestataireComponent} from "./list-prestataire/list-prestataire.component";
import {ServiceModule} from "../service/service.module";


@NgModule({
  declarations: [
    PrestataireComponent,
    RegisterPraistataireComponent,
    ListPrestataireComponent,
    EditPrestataireComponent
  ],
  exports: [
    RegisterPraistataireComponent,
    PrestataireComponent
  ],
  imports: [
    CommonModule,
    PrestataireRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    MatPaginatorModule,
    ServiceModule
  ]
})
export class PrestataireModule { }
