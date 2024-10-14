import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { CategoryComponent } from './Homes/category/category.component';
import {HomeComponent} from "./Homes/home/home.component";
import { NavBarComponent } from './Homes/nav-bar/nav-bar.component';
import { FooterComponent } from './Homes/footer/footer.component';
import { BlogComponent } from './Homes/blog/blog.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import { AboutComponent } from './Homes/about/about.component';
import { RegisterComponent } from './component/register/register.component';
import {ClientModule} from "./client/client.module";
import {PrestataireModule} from "./prestataire/prestataire.module";
import {LoginComponent} from "./component/login/login.component";
import {PageNotFoundComponent} from "./component/page-not-found/page-not-found.component";
import {LogoutComponent} from "./component/logout/logout.component";
import {Interceptor} from "./core/interceptor/interceptor.interceptor";
import { AddComponent } from './contact/add/add.component';
import { ListContactComponent } from './contact/list-contact/list-contact.component';
import { ReviewComponent } from './review/review/review.component';
import { FaqComponent } from './Homes/faq/faq.component';
import {MatPaginatorModule} from "@angular/material/paginator";

import {JwtModule} from '@auth0/angular-jwt'
import {ServiceModule} from "./service/service.module";
import { AddDemandeComponent } from './demande/add-demande/add-demande.component';
import { ListDemandeComponent } from './demande/list-demande/list-demande.component';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    LogoutComponent,
    PageNotFoundComponent,
    HomeComponent,
    CategoryComponent,
    NavBarComponent,
    FooterComponent,
    BlogComponent,
    AboutComponent,
    RegisterComponent,
    AddComponent,
    ListContactComponent,
    ReviewComponent,
    FaqComponent,
    AddDemandeComponent,
    ListDemandeComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    ClientModule,
    PrestataireModule,
    MatPaginatorModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: () => localStorage.getItem('token'),
      }
    }),
    ServiceModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: Interceptor,
      multi: true
    }
  ],
  exports: [
    FooterComponent,
    NavBarComponent,
    ListContactComponent,
    HomeComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
