import { Component } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { AnnonceService } from '../../services/shared/annonce.service';
import { Observable, switchMap } from 'rxjs';
import { IAnnonce } from '../../model/annonce';

@Component({
  selector: 'app-annonce-details',
  templateUrl: './annonce-details.component.html',
})
export class AnnonceDetailsComponent {
  annonce: IAnnonce = {
    id: '',
    title: '',
    description: '',
    price: 0,
    type: '',
    createdAt: '',
  };

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private annonceService: AnnonceService
  ) {}

  ngOnInit() {
    // this.route.paramMap
    //   .pipe(
    //     switchMap((params: ParamMap) =>
    //       this.annonceService.getAnnonceById(params.get('id')!)
    //     )
    //   )
    //   .subscribe((res) => (this.annonce = res));

    const id = this.route.snapshot.paramMap.get('id')!; // id (params)
    this.annonceService
      .getAnnonceById(id)
      .subscribe((res) => (this.annonce = res));

    // this.router.navigate(['annonce/filter', { id: id, foo: 'foo' }]); // redirect + provide some extra data
  }
}
