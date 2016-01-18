import {Component, OnInit} from "angular2/core";
import {Book} from "./book";
import {BookService} from "./book.service";
import {BookDetailComponent} from "./book-detail.component";

@Component({
    selector: 'my-app',
    template: `
    <h1>{{title}}</h1>
    <h2>Books</h2>
    <ul class="books">
        <li *ngFor="#book of books"
        [class.selected]="book === selectedBook"
        (click)="onSelect(book)">
            <span class="badge">{{book.id}}</span> {{book.name}}
        </li>
    </ul>
    <my-book-detail [book]="selectedBook"></my-book-detail>
    `,
    styles: [`
  .selected {
    background-color: #CFD8DC !important;
    color: white;
  }
  .books {
    margin: 0 0 2em 0;
    list-style-type: none;
    padding: 0;
    width: 10em;
  }
  .books li {
    cursor: pointer;
    position: relative;
    left: 0;
    background-color: #EEE;
    margin: .5em;
    padding: .3em 0em;
    height: 1.6em;
    border-radius: 4px;
  }
  .books li.selected:hover {
    color: white;
  }
  .books li:hover {
    color: #607D8B;
    background-color: #EEE;
    left: .1em;
  }
  .books .text {
    position: relative;
    top: -3px;
  }
  .books .badge {
    display: inline-block;
    font-size: small;
    color: white;
    padding: 0.8em 0.7em 0em 0.7em;
    background-color: #607D8B;
    line-height: 1em;
    position: relative;
    left: -1px;
    top: -4px;
    height: 1.8em;
    margin-right: .8em;
    border-radius: 4px 0px 0px 4px;
  }
    `],
    directives: [BookDetailComponent],
    providers: [BookService]
})
export class AppComponent implements OnInit {

    public title = 'Bokföring';
    public selectedBook: Book;
    public books: Book[];

    constructor(private _bookService: BookService) { }

    getBooks() {
        this._bookService.getBooks().then(books => this.books = books);
    }

    ngOnInit() {
        this.getBooks();
    }

    onSelect(book: Book) {
        this.selectedBook = book;
    }

}
