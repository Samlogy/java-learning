import { Routes } from '@angular/router';
import { AddComponent } from './pages/add/add.component';
import { FilterComponent } from './pages/filter/filter.component';
import { PageNotFoundComponent } from './pages/page-not-found/page-not-found.component';
import { AnnonceDetailsComponent } from './pages/annonce-details/annonce-details.component';
import { AuthGuard } from './guard/auth.service';

const appRoutes: Routes = [
  {
    path: 'annonce/filter',
    component: FilterComponent,
  },
  {
    path: 'annonce', // children routes / nested routes
    canActivate: [AuthGuard],
    children: [
      { path: 'add', component: AddComponent },
      { path: 'edit/:id', component: AddComponent },
    ],
  },
  {
    path: 'about', // lazy loading page
    loadComponent: () =>
      import('./pages/about/about.component').then((opt) => opt.AboutComponent),
  },
  { path: 'annonce/:id', component: AnnonceDetailsComponent }, // route with param id
  { path: '', redirectTo: 'annonce/filter', pathMatch: 'full' }, // redirect route
  { path: '**', component: PageNotFoundComponent }, // not found route (not match route)
];

export default appRoutes;
