package se.eris.accounting.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.eris.accounting.model.book.Book;
import se.eris.accounting.model.book.BookDescription;
import se.eris.accounting.model.book.BookId;
import se.eris.accounting.model.book.BookName;
import se.eris.accounting.services.BookRestFacade;
import se.eris.accounting.web.rest.model.RestBook;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/book", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookResource {

    private static final Logger logger = LoggerFactory.getLogger(BookResource.class);

        private final BookRestFacade bookRestFacade;

    @Autowired
    public BookResource(final BookRestFacade bookRestFacade) {
        this.bookRestFacade = bookRestFacade;
    }

        @RequestMapping(method = RequestMethod.GET)
    public List<RestBook> getAll() {
        return bookRestFacade.getAllBooks().map(RestBook::new).collect(Collectors.toList());
    }

        @RequestMapping(method = RequestMethod.GET, value = "/{bookId}")
    public ResponseEntity<RestBook> get(@PathVariable("bookId") final UUID bookId) {
        final Optional<Book> book = bookRestFacade.getAllBooks().filter(b -> b.getId().get().asUUID().equals(bookId)).findFirst();
        if (book.isPresent()) {
            return new ResponseEntity<>(new RestBook(book.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

        @RequestMapping(method = RequestMethod.GET, value = "/template")
    public RestBook getTemplate() {
        return new RestBook(new Book(Optional.empty(), BookName.of("template"), BookDescription.empty()));
    }

    // todo remove
        @RequestMapping(method = RequestMethod.GET, value = "/create")
    public RestBook createRandom(@RequestParam("name") final String name, @RequestParam("description") final String description) {
        return new RestBook(bookRestFacade.create(new Book(Optional.empty(), BookName.of(name), BookDescription.of(description))));
    }

        @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<RestBook> create(@RequestBody final RestBook restBook) {
        final Book saved = bookRestFacade.create(restBook.toCore());
        logger.debug("created book: " + saved);
        return new ResponseEntity<>(new RestBook(saved), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{bookId}")
    public ResponseEntity delete(@PathVariable("bookId") final UUID bookId) {
        bookRestFacade.delete(BookId.from(bookId));
        return ResponseEntity.ok().build();
    }
}
