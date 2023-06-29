import { Component, OnChanges } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
})
export class NavbarComponent {
  isVisible: boolean = true;

  toggleMenu() {
    this.isVisible = this.isVisible ? false : true;
    console.log(this.isVisible);
  }
}
