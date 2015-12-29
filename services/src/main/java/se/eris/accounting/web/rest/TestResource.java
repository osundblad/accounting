package se.eris.accounting.web.rest;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.eris.accounting.model.book.Book;
import se.eris.accounting.model.book.BookDescription;
import se.eris.accounting.model.book.BookName;
import se.eris.accounting.web.rest.model.RestAccountInfo;
import se.eris.accounting.web.rest.model.RestBook;
import se.eris.accounting.web.rest.model.RestBookYear;
import se.eris.accounting.web.rest.model.RestBookYearAccount;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/test", produces = MediaType.APPLICATION_JSON_VALUE)
public class TestResource {

    private static final Logger logger = LoggerFactory.getLogger(TestResource.class);

    @NotNull
    private final BookResource bookResource;

    @NotNull
    private final BookYearResource bookYearResource;

    @Autowired
    public TestResource(@NotNull final BookResource bookResource, @NotNull final BookYearResource bookYearResource) {
        this.bookResource = bookResource;
        this.bookYearResource = bookYearResource;
    }

    @SuppressWarnings("FeatureEnvy")
    @NotNull
    @RequestMapping(method = RequestMethod.GET, value = "/run/{bookName}")
    public ResponseEntity<String> run(@PathVariable("bookName") final String bookName) {
        logger.info("creating book");
        final RestBook book = bookResource.create(new RestBook(new Book(Optional.empty(), BookName.of(bookName), BookDescription.of("This book was created by the test script.")))).getBody();
        logger.info("  created book: '" + bookName + "'");

        final RestBookYear bookYear1 = bookYearResource.create(new RestBookYear(null, book.getBookId().get().asUUID(), "2014-07-01", "2015-06-30")).getBody();
        logger.info("  created year: '" + bookYear1.toCore().toString() + "'");
        final RestBookYear bookYear2 = bookYearResource.create(bookYearResource.getNext(book.getBookId().get().asUUID())).getBody();
        logger.info("  created year: '" + bookYear2.toCore().toString() + "'");

        final RestBookYearAccount account = bookYearResource.createAccount(new RestBookYearAccount(null, bookYear1.getId().get(), new RestAccountInfo("1234", "Bank", "Banken med alla pengarna")));

        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @NotNull
    @RequestMapping(method = RequestMethod.GET, value = "/delete/{bookName}")
    public ResponseEntity delete(@PathVariable("bookName") final String bookName) {
        final Optional<Book> book = getBookByName(bookName);
        if (book.isPresent()) {
            logger.info("deleting book: '" + bookName + "'");
            deleteAllBookYears(book.get());
            bookResource.delete(book.flatMap(Book::getId).get().asUUID());
            logger.info("  deleted book: '" + bookName + "'");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            logger.info("deleting book: '" + bookName + "' not found");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    private void deleteAllBookYears(@NotNull final Book book) {
        for (final RestBookYear year : bookYearResource.get(book.getId().get().asUUID())) {
            logger.info("  deleting year: '" + year.toString() + "'");
            deleteAllAccounts(year);
            bookYearResource.delete(year.getId().get());
        }
    }

    private void deleteAllAccounts(@NotNull final RestBookYear year) {
        for (final RestBookYearAccount account : bookYearResource.getAccounts(year.getId().get())) {
            logger.info("  deleting account: '" + account.toString() + "'");
            bookYearResource.deleteAccount(account.getId().get().asUUID());
        }
    }

    @NotNull
    private Optional<Book> getBookByName(@NotNull final String bookName) {
        return bookResource.getAll().stream().map(RestBook::toCore).filter(b -> b.getName().equals(BookName.of(bookName))).findFirst();
    }

}
