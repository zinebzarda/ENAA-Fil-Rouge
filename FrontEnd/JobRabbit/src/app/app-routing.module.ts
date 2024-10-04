import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {LogoutComponent} from "./logout/logout.component";
import {PageNotFoundComponent} from "./page-not-found/page-not-found.component";
import {HomeComponent} from "./Homes/home/home.component";
import {CategoryComponent} from "./Homes/category/category.component";
import {FooterComponent} from "./Homes/footer/footer.component";
import {NavBarComponent} from "./Homes/nav-bar/nav-bar.component";
import {BlogComponent} from "./Homes/blog/blog.component";
import {AboutComponent} from "./Homes/about/about.component";

const routes: Routes = [
  { path: 'admin', loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule) },
  { path: 'client', loadChildren: () => import('./client/client.module').then(m => m.ClientModule) },
  { path: 'prestataire', loadChildren: () => import('./prestataire/prestataire.module').then(m => m.PrestataireModule) },
  { path: 'register', loadChildren: () => import('./register/register.module').then(m => m.RegisterModule) },
  { path: 'login', component: LoginComponent },
  { path: 'logout', component: LogoutComponent },
  { path: 'navBar', component:NavBarComponent},
  { path: 'category', component:CategoryComponent},
  { path: 'blog', component:BlogComponent},
  { path: 'about', component:AboutComponent},
  { path: 'footer', component:FooterComponent},
  { path: 'home', component:HomeComponent},
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  // { path: '', redirectTo: 'login', pathMatch: 'full' },
  // { path: 'register', component: RegisterComponent},
  { path: '**', component: PageNotFoundComponent }
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

