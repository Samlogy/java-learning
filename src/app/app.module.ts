import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { AddComponent } from './pages/add/add.component';
import { AnnonceDetailsComponent } from './pages/annonce-details/annonce-details.component';
import { AppComponent } from './app.component';
import { FilterComponent } from './pages/filter/filter.component';
import { PageNotFoundComponent } from './pages/page-not-found/page-not-found.component';
import appRoutes from './app-routing.module';
import { TableComponent } from './components/table/table.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { PaginationComponent } from './components/pagination/pagination.component';

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
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule,
    RouterModule.forRoot(appRoutes),
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
