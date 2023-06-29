import { Component, Input } from '@angular/core';
import { IAnnonce } from '../filter/filter.component';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css'],
})
export class TableComponent {
  @Input()
  annonces!: IAnnonce[];
}
