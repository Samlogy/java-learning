import { Routes } from '@angular/router';
import { AddComponent } from './pages/add/add.component';
import { FilterComponent } from './pages/filter/filter.component';
import { PageNotFoundComponent } from './pages/page-not-found/page-not-found.component';
import { AnnonceDetailsComponent } from './pages/annonce-details/annonce-details.component';

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
