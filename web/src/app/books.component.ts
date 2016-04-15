import {Component, OnInit} from "angular2/core";
import {Router} from "angular2/router";

import {Book} from "./book";
import {BookService} from "./book.service";

@Component({
    selector: 'my-books',
    templateUrl: 'app/books.component.html',
    styleUrls: ['app/books.component.css'],
})
export class BooksComponent implements OnInit {

    books:Book[];
    selectedBook:Book;
    private errorMessage;

    constructor(private _router:Router,
                private _bookService:BookService) {
    }

    ngOnInit() {
        this.getBooks();
    }

    getBooks() {
        this._bookService.getBooks()
            .subscribe(
                heroes => this.books = heroes,
                error => this.errorMessage = <any>error);
    }

    addBook2() {
        this.addBook('hej')
    }

    addBook(name:string) {
        if (!name) {
            return;
        }
        this._bookService.createBook(name)
            .subscribe(
                hero => this.books.push(hero),
                error => this.errorMessage = <any>error);
    }

    onSelect(book:Book) {
        this.selectedBook = book;
    }

    gotoDetail() {
        this._router.navigate(['BookDetail', {id: this.selectedBook.id}]);
    }

}
