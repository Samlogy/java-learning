import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { IAnnonce } from 'src/app/model/annonce';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
})
export class AddComponent implements OnInit {
  pageTitle: string = 'ADD';
  reactiveForm!: FormGroup;
  annonce: IAnnonce;
  annonceService: any;

  types = ['VEHICULE', 'EMPLOI', 'IMMOBILLIER'];
  // isSubmitted: false | undefined;

  constructor(private route: ActivatedRoute, private router: Router) {
    this.annonce = {
      title: '',
      description: '',
      price: 0,
      type: '',
    } as IAnnonce;
  }

  ngOnInit(): void {
    this.reactiveForm = new FormGroup({
      title: new FormControl(this.annonce.title, [Validators.required]),
      description: new FormControl(this.annonce.description, [
        Validators.required,
      ]),
      price: new FormControl(this.annonce.price, [Validators.required]),
      type: new FormControl(this.annonce.type, [Validators.required]),
    });

    this.pageTitle = this.route.snapshot.paramMap.has('id') ? 'Edit' : 'Add';
  }

  get title() {
    return this.reactiveForm.get('title')!;
  }

  get description() {
    return this.reactiveForm.get('description')!;
  }

  get type() {
    return this.reactiveForm.get('type')!;
  }

  get price() {
    return this.reactiveForm.get('price')!;
  }

  changeType(e: any) {
    console.log(e.value);
    this.type.setValue(e.target.value, {
      onlySelf: true,
    });
  }

  public validate(): void {
    if (this.reactiveForm.invalid) {
      for (const control of Object.keys(this.reactiveForm.controls)) {
        this.reactiveForm.controls[control].markAsTouched();
      }
      return;
    }

    this.annonce = this.reactiveForm.value;
  }

  createAnnonce(): void {
    this.validate(); // form validation

    const newAnnonce = {
      title: this.annonce.title,
      description: this.annonce.description,
      type: this.annonce.type,
      price: this.annonce.price,
    };

    console.log(newAnnonce);

    this.annonceService.createAnnonce(newAnnonce).subscribe({
      next: (res: any) => {
        console.log(res);
        // this.submitted = true;
      },
      error: (err: any) => console.error('Err annonce POST: ', err),
    });
  }
}
