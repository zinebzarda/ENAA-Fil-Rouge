import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HomeComponent } from "./Homes/home/home.component";
import { CategoryComponent } from "./Homes/category/category.component";
import { FooterComponent } from "./Homes/footer/footer.component";
import { NavBarComponent } from "./Homes/nav-bar/nav-bar.component";
import { BlogComponent } from "./Homes/blog/blog.component";
import { AboutComponent } from "./Homes/about/about.component";
import {RegisterComponent} from "./component/register/register.component";
import {LoginComponent} from "./component/login/login.component";
import {LogoutComponent} from "./component/logout/logout.component";
import {PageNotFoundComponent} from "./component/page-not-found/page-not-found.component";
import {AddComponent} from "./contact/add/add.component";
import {ListContactComponent} from "./contact/list-contact/list-contact.component";
import {FaqComponent} from "./Homes/faq/faq.component";
import {AddDemandeComponent} from "./demande/add-demande/add-demande.component";

const routes: Routes = [
  { path: 'admin', loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule) },
  { path: 'client', loadChildren: () => import('./client/client.module').then(m => m.ClientModule) },
  { path: 'prestataire', loadChildren: () => import('./prestataire/prestataire.module').then(m => m.PrestataireModule) },
  { path: 'service', loadChildren: () => import('./service/service.module').then(m => m.ServiceModule) },
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: 'logout', component: LogoutComponent },
  { path: 'navBar', component: NavBarComponent },
  { path: 'category', component: CategoryComponent },
  { path: 'blog', component: BlogComponent },
  { path: 'about', component: AboutComponent },
  { path: 'faq', component: FaqComponent },
  { path: 'footer', component: FooterComponent },
  { path: 'addContact', component: AddComponent },
  { path: 'book/:id', component: AddDemandeComponent },
  { path: 'home', component: HomeComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
