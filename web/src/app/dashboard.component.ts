import {Component, OnInit} from 'angular2/core';
import {Router} from "angular2/router";

import {Book} from './book';
import {BookService} from './book.service';

@Component({
    selector: 'my-dashboard',
    templateUrl: 'app/dashboard.component.html',
    styleUrls: ['app/dashboard.component.css'],
})
export class DashboardComponent implements OnInit {

    books:Book[] = [];
    private errorMessage;

    constructor(private _router:Router,
                private _bookService:BookService) {
    }

    ngOnInit() {
        this._bookService.getBooks()
            .subscribe(
                heroes => this.books = heroes.slice(0, 4),
                error => this.errorMessage = <any>error);
    }

    gotoDetail(book:Book) {
        let link = ['BookDetail', {id: book.id}];
        this._router.navigate(link);
    }

}
