import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

const BASE_URL = 'http://localhost:8080/api/annonce';

@Injectable({
  providedIn: 'root',
})
export class AnnonceService {
  constructor(private http: HttpClient) {}

  onSearch(
    title?: string,
    description?: string,
    priceMin?: number,
    priceMax?: number,
    type?: string
  ) {
    return this.http.get(BASE_URL).subscribe((res) => console.log(res));
  }
}

// + `/filter?title=${title}&description=${description}&priceMin=${priceMin}&priceMax=${priceMax}&type=${type}`
