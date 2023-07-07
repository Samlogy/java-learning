import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { IAnnonce } from '../../model/annonce';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { environmentDev } from '../../../environments/environment.development';

const BASE_URL = environment ? environment.apiUrl : environmentDev.apiUrl;

@Injectable({
  providedIn: 'root',
})
export class AnnonceService {
  constructor(private http: HttpClient) {}

  onFilter(
    title?: string,
    description?: string,
    priceMin?: number,
    priceMax?: number,
    type?: string
  ) {
    console.log('ENV: ', environment.production);
    console.log('URL: ', environment.apiUrl);
    return this.http.get(
      BASE_URL +
        `/filter?title=${title}&description=${description}&priceMin=${priceMin}&priceMax=${priceMax}&type=${type}`
    );
  }

  getAnnonces(): any {
    return this.http.get<IAnnonce[]>(BASE_URL);
  }
  getAnnonceById(id: string): Observable<IAnnonce> {
    return this.http.get<IAnnonce>(BASE_URL + `/${id}`);
  }
  createAnnonce(annonce: IAnnonce): Observable<IAnnonce> {
    return this.http.post<IAnnonce>(BASE_URL, annonce);
  }
  updateAnnonceById(id: string, annonce: IAnnonce) {
    return this.http.put<IAnnonce>(BASE_URL + `${id}`, annonce);
  }
  deleteAnnonceById(id: string): Observable<void> {
    return this.http.delete<void>(BASE_URL + `/${id}`);
  }
  patchAnnonceById(id: string, annonce: IAnnonce): Observable<IAnnonce> {
    return this.http.patch<IAnnonce>(BASE_URL + `${id}`, annonce);
  }
}
