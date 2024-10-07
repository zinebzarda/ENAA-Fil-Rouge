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
import {HttpClientModule} from "@angular/common/http";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import { AboutComponent } from './Homes/about/about.component';
import { RegisterComponent } from './component/register/register.component';
import {ClientModule} from "./client/client.module";
import {PrestataireModule} from "./prestataire/prestataire.module";
import {LoginComponent} from "./component/login/login.component";
import {PageNotFoundComponent} from "./component/page-not-found/page-not-found.component";
import {LogoutComponent} from "./component/logout/logout.component";
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
  ],
  providers: [],
  exports: [
    FooterComponent,
    NavBarComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
