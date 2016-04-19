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
    isAddBook:Boolean = false;
    private errorMessage;

    constructor(private _router:Router,
                private _bookService:BookService) {
    }

    ngOnInit() {
        this.getBooks();
    }

    showAddBook(show) {
        this.isAddBook = show;
    }

    getBooks() {
        this._bookService.getBooks()
            .subscribe(
                books => this.books = books,
                error => this.errorMessage = <any>error);
    }

    addBook(name:string) {
        if (!name) {
            return;
        }
        this._bookService.createBook(name)
            .subscribe(
                book => this.books.push(book),
                error => this.errorMessage = <any>error);
        this.showAddBook(false);
    }

    onSelect(book:Book) {
        this.showAddBook(false);
        this.selectedBook = book;
    }

    gotoDetail() {
        this._router.navigate(['BookDetail', {id: this.selectedBook.id}]);
    }

}
