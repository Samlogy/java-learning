import { Component, Input } from '@angular/core';
import { IAnnonce } from '../../model/annonce';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
})
export class TableComponent {
  @Input()
  annonces!: IAnnonce[];
}
