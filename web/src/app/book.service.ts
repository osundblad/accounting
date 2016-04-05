import {Injectable} from "angular2/core";
import {Http} from "angular2/http";
import {Book} from "./book";
import {STUB_BOOKS} from "./mock-books";

@Injectable()
export class BookService {

    http: Http;
    books:Book[] = [];

    constructor(http: Http) {
        this.http = http;
    }

    getBooks() {
        this.http.get('api/book/')
            // Call map on the response observable to get the parsed people object
            .map(res => res.json())
            // Subscribe to the observable to get the parsed books object and attach it to the
            // component
            .subscribe(
                books => this.setBooks(books),
                err => this.logError(err),
                () => console.log('Request Complete')
            );
    }

    setBooks(books) {
        console.warn('Successfully fetched: ', books);
        this.books = books;
    }

    logError(err) {
        console.error('There was an error: ', err);
    }

    // See the "Take it slow" appendix
    getBooksStub() {
        return new Promise<Book[]>(resolve =>
            setTimeout(()=>resolve(STUB_BOOKS), 2000) // 2 seconds
        );
    }

}