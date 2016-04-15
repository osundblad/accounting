import {Injectable} from "angular2/core";
import {Http, Response, RequestOptions, Headers} from 'angular2/http';
import {Observable} from "rxjs/Observable";

import {Book} from "./book";

@Injectable()
export class BookService {

    constructor(private _http:Http) {
    }

    getBook(id:string) {
        return this._http.get(this._bookUrl)
            .map(res => <Book> res.json().filter(book => book.id === id)[0])
            .catch(this.handleError);
    }

    private _bookUrl = 'http://localhost:8080/api/book';

    getBooks() : Observable<Book[]> {
        return this._http.get(this._bookUrl)
            .map(res => <Book[]> res.json())
            .catch(this.handleError);
    }

    createBook(name: string) : Observable<Book> {
        let body = JSON.stringify({ id: null, "name": name, "description" : "" });
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });

        return this._http.post(this._bookUrl, body, options)
            .map(res =>  <Book> res.json())
            .catch(this.handleError);
    }

    private handleError(error:Response) {
        // in a real world app, we may send the error to some remote logging infrastructure
        // instead of just logging it to the console
        console.error(error);
        return Observable.throw(error.json().error || 'Server error');
    }
    
}