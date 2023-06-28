import { Component, OnInit } from '@angular/core';
import { AnnonceService } from '../shared/annonce.service';

export interface IAnnonce {
  id?: string;
  title: string;
  description: string;
  price: number;
  type: string;
  createdAt?: string;
}

const DATA = [
  {
    id: '8b769ca9-89c4-4ff9-9ed4-9c9a6054fa67',
    title: 'cccccccc',
    description: 'Spacious 3-bedroom house for sale',
    price: 0.0,
    type: 'EMPLOI',
    createdAt: '2023-06-20',
  },
  {
    id: '6e310a1e-f1c8-4fe5-b06f-120b844e3fbe',
    title: 'cccccccc',
    description: 'Spacious 3-bedroom house for sale',
    price: 3000.0,
    type: 'EMPLOI',
    createdAt: '2023-06-20',
  },
  {
    id: 'dbfd8c68-6f7b-477b-86bf-21a51140372c',
    title: 'cccccccc',
    description: 'Spacious 3-bedroom house for sale',
    price: 100000.0,
    type: 'EMPLOI',
    createdAt: '2023-06-20',
  },
  {
    id: '1d8c1ef0-299a-4d37-aad2-e0cf2b650962',
    title: 'New Annonce 1 updated',
    description: 'Spacious 3-bedroom house for sale',
    price: 100000.0,
    type: 'VEHICULE',
    createdAt: '2023-06-19',
  },
  {
    id: '1ab3fab0-eafa-44b2-ae42-1be693705c39',
    title: 'azeaze patched',
    description: 'Spacious 3-bedroom house for sale',
    price: 50000.0,
    type: 'EMPLOI',
    createdAt: '2023-06-19',
  },
  {
    id: 'b6e4532b-c854-4fe5-acb8-212717dbf438',
    title: 'cccccccc patched',
    description: 'Spacious 3-bedroom  patched',
    price: 50000.0,
    type: 'EMPLOI',
    createdAt: '2023-06-19',
  },
  {
    id: 'ffa1a2bb-2516-4c17-b462-122f7e1b1f8f',
    title: 'Title 0',
    description: 'description 0 ...',
    price: 150.0,
    type: 'VEHICULE',
    createdAt: '2023-06-26',
  },
  {
    id: '87ad28cf-427e-4c9b-af49-a50d4bcd09bf',
    title: 'Title 0',
    description: 'description 0 ...',
    price: 150.0,
    type: 'VEHICULE',
    createdAt: null,
  },
  {
    id: 'd2fa7d81-4c7b-4915-8d9e-eb8145a6c828',
    title: 'Title 0',
    description: 'description 0 ...',
    price: 150.0,
    type: 'VEHICULE',
    createdAt: '2023-06-26',
  },
  {
    id: 'a8e90ce6-3975-4f2a-987b-040c6e93a999',
    title: 'Title 0',
    description: 'description 0 ...',
    price: 150.0,
    type: 'VEHICULE',
    createdAt: null,
  },
  {
    id: 'd0d5566d-e062-4df0-b575-1dcbbd69cc45',
    title: 'Title 0',
    description: 'description 0 ...',
    price: 150.0,
    type: 'VEHICULE',
    createdAt: '2023-06-26',
  },
  {
    id: '7d2b0b47-299d-4967-978e-9eb5f5b4331c',
    title: 'Title 0',
    description: 'description 0 ...',
    price: 150.0,
    type: 'VEHICULE',
    createdAt: null,
  },
];

@Component({
  selector: 'app-filter',
  templateUrl: './filter.component.html',
  styleUrls: ['./filter.component.css'],
})
export class FilterComponent {
  annonces: IAnnonce[] = [];

  constructor(private annonceService: AnnonceService) {}

  // ngOnInit(): void {
  //   this.annonces = this.annonceService.getAnnonces();
  // }

  onFilter(
    title: any,
    description: any,
    priceMin: any,
    priceMax: any,
    type: any
  ) {
    const observer = this.annonceService.onFilter(
      title.value,
      description.value,
      priceMin.value,
      priceMax.value,
      type.value
    );
    console.log(observer);
    observer.subscribe((res: any) => (this.annonces = res));
  }

  onReset(
    title: any,
    description: any,
    priceMin: any,
    priceMax: any,
    type: any
  ) {
    title.value = '';
    description.value = '';
    priceMin.value = '';
    priceMax.value = '';
    type.value = '';
  }
}
