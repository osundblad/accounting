import {Injectable} from "angular2/core";
import {Book} from "./book";
import {STUB_BOOKS} from "./mock-books";

@Injectable()
export class BookService {

    getBooks() {
        return Promise.resolve(STUB_BOOKS);
    }

    getBooksSlowly() {
        return new Promise<Book[]>(resolve =>
            setTimeout(()=>resolve(STUB_BOOKS), 2000) // 2 seconds
        );
    }

    getBook(id: number) {
        return Promise.resolve(STUB_BOOKS).then(
            books => books.filter(book => book.id === id)[0]
        );
    }
}