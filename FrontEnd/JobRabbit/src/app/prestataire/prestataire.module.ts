import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PrestataireRoutingModule } from './prestataire-routing.module';
import { PrestataireComponent } from './prestataire.component';
import { RegisterPraistataireComponent } from './register-praistataire/register-praistataire.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { ListComponent } from './list/list.component';
import { EditComponent } from './edit/edit.component';


@NgModule({
  declarations: [
    PrestataireComponent,
    RegisterPraistataireComponent,
    ListComponent,
    EditComponent
  ],
  exports: [
    RegisterPraistataireComponent,
    PrestataireComponent
  ],
  imports: [
    CommonModule,
    PrestataireRoutingModule,
    ReactiveFormsModule,
    FormsModule
  ]
})
export class PrestataireModule { }
