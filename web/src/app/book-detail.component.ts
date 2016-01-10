import {Component} from 'angular2/core';

import {Book} from './book';

@Component({
    selector: 'my-book-detail',
    inputs: ['book'],
    template: `
    <div *ngIf="book">
        <h2>{{book.name}}</h2>
        <div><label>id: </label>{{book.id}}</div>
        <div>
            <label>name: </label>
            <div><input [(ngModel)]="book.name" placeholder="name"></div>
        </div>
    </div>
    `,
})
export class BookDetailComponent {
    public book: Book;
}

