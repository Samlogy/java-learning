import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { AddComponent } from './add/add.component';
import { AnnonceDetailsComponent } from './annonce-details/annonce-details.component';
import { AppComponent } from './app.component';
import { FilterComponent } from './filter/filter.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import appRoutes from './routerConfig';
import { TableComponent } from './table/table.component';
import { NavbarComponent } from './navbar/navbar.component';
import { PaginationComponent } from './pagination/pagination.component';

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
