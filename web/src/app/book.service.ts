import {Injectable} from 'angular2/core';

import {Book} from "./book";
import {BOOKS} from './mock-books';

@Injectable()
export class BookService {

    getBooks() {
        return Promise.resolve(BOOKS);
    }

    // See the "Take it slow" appendix
    getHeroesSlowly() {
        return new Promise<Book[]>(resolve =>
            setTimeout(()=>resolve(BOOKS), 2000) // 2 seconds
        );
    }

}