import {Component} from 'angular2/core';
import {NgForm}    from 'angular2/common';
import { Book }    from './book';

@Component({
    selector: 'book-form',
    templateUrl: 'app/book-form.component.html'
})
export class BookFormComponent {

    powers = ['Really Smart', 'Super Flexible', 'Super Hot', 'Weather Changer'];
    model = new Book("a803b71c-2c2a-4306-acb9-fe8610983808", 'Dr IQ', 'Chuck Overstreet');
    submitted = false;
    onSubmit() { this.submitted = true; }
}
