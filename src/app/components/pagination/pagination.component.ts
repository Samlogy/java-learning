import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
})
export class PaginationComponent {
  @Input()
  totalPages = 0;
  @Input()
  currentPage = 1;

  pages: any[] = [];

  ngOnInit(): void {
    this.pages = [...Array(this.totalPages - this.currentPage + 1).keys()].map(
      (x) => x + this.currentPage
    );
  }
}
