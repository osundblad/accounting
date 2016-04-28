import {Component} from 'angular2/core';
import {NgForm}    from 'angular2/common';
import { Book }    from './book';
import {BookService} from "./book.service";

@Component({
    selector: 'book-form',
    templateUrl: 'app/book-form.component.html',
    styleUrls: ['app/book-form.component.css'],
})
export class BookFormComponent {

    active = true;
    model = new Book('', '', '');
    submitted = false;

    constructor(private _bookService:BookService) {
    }

    onSubmit() {
        this.submitted=true;
        // this._bookService.createBook(name)
        //     .subscribe(
        //         book => this.createdBook(book),
        //         error => this.errorMessage = <any>error);
    }

    newBook() {
        this.model = new Book('', '', '');
        this.active = false;
        setTimeout(()=> this.active=true, 0);
    }

}
