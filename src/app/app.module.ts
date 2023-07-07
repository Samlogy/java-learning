import { HttpClientModule } from '@angular/common/http';
import { NgModule, APP_INITIALIZER } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import appRoutes from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { PaginationComponent } from './components/pagination/pagination.component';
import { TableComponent } from './components/table/table.component';
import { AddComponent } from './pages/add/add.component';
import { AnnonceDetailsComponent } from './pages/annonce-details/annonce-details.component';
import { FilterComponent } from './pages/filter/filter.component';
import { PageNotFoundComponent } from './pages/page-not-found/page-not-found.component';
import { AboutComponent } from './pages/about/about.component';
import { KeycloakAngularModule, KeycloakService } from 'keycloak-angular';
import { initializer } from './app-init';

@NgModule({
  declarations: [
    AppComponent,
    FilterComponent,
    AddComponent,
    PageNotFoundComponent,
    AnnonceDetailsComponent,
    TableComponent,
    NavbarComponent,
    PaginationComponent,
    AboutComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule,
    KeycloakAngularModule, // keycloak module
    RouterModule.forRoot(appRoutes),
  ],
  providers: [
    // add this provider
    {
      provide: APP_INITIALIZER,
      useFactory: initializer,
      deps: [KeycloakService],
      multi: true,
    },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
