import { Routes } from '@angular/router';
import { AddComponent } from './add/add.component';
import { FilterComponent } from './filter/filter.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { AnnonceDetailsComponent } from './annonce-details/annonce-details.component';

const appRoutes: Routes = [
  {
    path: 'annonce/filter',
    component: FilterComponent,
  },
  {
    path: 'annonce/add',
    component: AddComponent,
  },
  { path: 'annonce/:id', component: AnnonceDetailsComponent },
  { path: '', redirectTo: 'annonce/filter', pathMatch: 'full' },
  { path: '**', component: PageNotFoundComponent },
];

export default appRoutes;
