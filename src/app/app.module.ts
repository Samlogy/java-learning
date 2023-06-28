import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AddComponent } from './add/add.component';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FilterComponent } from './filter/filter.component';
import { RouterModule, Routes } from '@angular/router';
import appRoutes from './routerConfig';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { AnnonceDetailsComponent } from './annonce-details/annonce-details.component';

@NgModule({
  declarations: [AppComponent, FilterComponent, AddComponent, PageNotFoundComponent, AnnonceDetailsComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    RouterModule,
    RouterModule.forRoot(appRoutes),
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
