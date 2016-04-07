import {Injectable} from "angular2/core";
import {Http, Response} from 'angular2/http';
import {Observable} from "rxjs/Observable";

import {Book} from "./book";

@Injectable()
export class BookService {

    constructor(private _http:Http) {
    }

    getBook(id:string) {
        return this._http.get('http://localhost:8080/api/book')
            .map(res => <Book> res.json().filter(book => book.id === id)[0])
            .catch(this.handleError);
    }

    getBooks() {
        return this._http.get('http://localhost:8080/api/book')
            .map(res => <Book[]> res.json())
            .catch(this.handleError);
    }

    private handleError(error:Response) {
        // in a real world app, we may send the error to some remote logging infrastructure
        // instead of just logging it to the console
        console.error(error);
        return Observable.throw(error.json().error || 'Server error');
    }
    
}