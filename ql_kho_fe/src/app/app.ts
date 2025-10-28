import {Component, signal} from '@angular/core';
import {Home} from './home/home';

@Component({
  selector: 'app-root',
  imports: [Home],
  template: `
    <main>
      <header class="brand-name">
        <img class="brand-logo" src="/assets/logo.svg" alt="logo" aria-hidden="true">
      </header>
      <section class="content">
        <app-home></app-home>
      </section>
    </main>
  `,
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('ql_kho_fe');
}
