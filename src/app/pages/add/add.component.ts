import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { IAnnonce } from '../../model/annonce';
import { AnnonceService } from '../../services/shared/annonce.service';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
})
export class AddComponent implements OnInit {
  pageTitle: string = '';
  reactiveForm!: FormGroup;
  annonce: IAnnonce;

  types: string[] = ['VEHICULE', 'EMPLOI', 'IMMOBILLIER'];
  isSubmitted: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private annonceService: AnnonceService
  ) {
    this.annonce = {
      title: '',
      description: '',
      price: 0,
      type: '',
    } as IAnnonce;
  }

  ngOnInit(): void {
    // form validation rules
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

  // validation functions
  public validate(): boolean {
    if (this.reactiveForm.invalid) {
      for (const control of Object.keys(this.reactiveForm.controls)) {
        this.reactiveForm.controls[control].markAsTouched();
      }
      return false;
    }

    this.annonce = this.reactiveForm.value;
    return true;
  }

  createAnnonce(): void {
    if (!this.validate()) return; // form validation

    this.annonceService.createAnnonce(this.annonce).subscribe({
      next: (res: any) => {
        console.log(res);
        // this.submitted = true;
      },
      error: (err: any) => console.error('Err annonce POST: ', err),
    });
  }
}
