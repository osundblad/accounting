package se.eris.accounting.web.rest;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import se.eris.accounting.model.book.Book;
import se.eris.accounting.model.book.BookDescription;
import se.eris.accounting.model.book.BookName;
import se.eris.accounting.web.rest.model.RestBook;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/test", produces = MediaType.APPLICATION_JSON_VALUE)
public class TestResource {

    private static final Logger logger = LoggerFactory.getLogger(TestResource.class);

    @NotNull
    private final BookResource bookResource;

    @Autowired
    public TestResource(@NotNull final BookResource bookResource) {
        this.bookResource = bookResource;
    }

    @NotNull
    @RequestMapping(method = RequestMethod.GET, value = "/run/{bookName}")
    public RestBook run(@PathVariable("bookName") final String bookName) {
        logger.info("creating book: '" + bookName + "'");
        return bookResource.create(new RestBook(new Book(Optional.empty(), BookName.of(bookName), BookDescription.of("This book was created by the test script."))));
    }

    @NotNull
    @RequestMapping(method = RequestMethod.GET, value = "/delete/{bookName}")
    public ResponseEntity delete(@PathVariable("bookName") final String bookName) {
        return bookResource.delete(bookName);
    }


}
