import {Component} from 'angular2/core';
import {RouteConfig, ROUTER_DIRECTIVES, ROUTER_PROVIDERS} from 'angular2/router';
import {HTTP_PROVIDERS} from "angular2/http";

import {BookService}     from './book.service';

import {DashboardComponent} from './dashboard.component';
import {BooksComponent} from './books.component';
import {BookDetailComponent} from "./book-detail.component";
import {BookFormComponent} from './book-form.component'

@Component({
    selector: 'my-app',
    template: `
        <h1>{{title}}</h1>
        <nav>
            <a [routerLink]="['Dashboard']">Dashboard</a>
            <a [routerLink]="['Books']">Books</a>
        </nav>
        <router-outlet></router-outlet>
    `,
    styleUrls: ['app/app.component.css'],
    directives: [
        ROUTER_DIRECTIVES],
    providers: [
        ROUTER_PROVIDERS,
        HTTP_PROVIDERS,
        BookService,
    ]
})
@RouteConfig([
    {
        path: '/dashboard',
        name: 'Dashboard',
        component: DashboardComponent,
        useAsDefault: true
    },
    {
        path: '/books',
        name: 'Books',
        component: BooksComponent
    },
    {
        path: '/detail/:id',
        name: 'BookDetail',
        component: BookDetailComponent
    },
])
export class AppComponent {
    title = 'Accounting';
}
