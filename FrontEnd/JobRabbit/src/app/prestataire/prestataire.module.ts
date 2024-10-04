import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PrestataireRoutingModule } from './prestataire-routing.module';
import { PrestataireComponent } from './prestataire.component';
import { RegisterPraistataireComponent } from './register-praistataire/register-praistataire.component';
import {ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    PrestataireComponent,
    RegisterPraistataireComponent
  ],
  exports: [
    RegisterPraistataireComponent
  ],
  imports: [
    CommonModule,
    PrestataireRoutingModule,
    ReactiveFormsModule
  ]
})
export class PrestataireModule { }
