package se.eris.accounting.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.eris.accounting.model.book.BookId;
import se.eris.accounting.model.book.BookYear;
import se.eris.accounting.model.book.BookYearId;
import se.eris.accounting.services.BookRestFacade;
import se.eris.accounting.web.rest.model.RestBookYear;
import se.eris.jtype.type.OpenDatePeriod;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/bookyear", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookYearResource {

    private static final Logger logger = LoggerFactory.getLogger(BookYearResource.class);

        private final BookRestFacade bookRestFacade;

    @Autowired
    public BookYearResource(final BookRestFacade bookRestFacade) {
        this.bookRestFacade = bookRestFacade;
    }

        @RequestMapping(method = RequestMethod.GET, value = "/{bookId}")
    public List<RestBookYear> get(@PathVariable("bookId") final UUID bookId) {
        return bookRestFacade.getBookYears(BookId.from(bookId)).map(RestBookYear::new).collect(Collectors.toList());
    }

        @RequestMapping(method = RequestMethod.GET, value = "/{bookId}/next")
    public ResponseEntity<RestBookYear> getNext(@PathVariable("bookId") final UUID bookId) {
        final BookId fromBookId = BookId.from(bookId);
        final Optional<BookYear> bookYear = bookRestFacade.getBookYears(fromBookId).sorted(BookYear.NEW_TO_OLD).findFirst();
        final OpenDatePeriod datePeriod = getNextYearDatePeriod(bookYear);
        return new ResponseEntity<>(new RestBookYear(new BookYear(Optional.empty(), fromBookId, datePeriod)), HttpStatus.OK);
    }

        private OpenDatePeriod getNextYearDatePeriod(final Optional<BookYear> bookYear) {
        final OpenDatePeriod datePeriod;
        if (bookYear.isPresent()) {
            final LocalDate previousEndDate = bookYear.get().getEndDate();
            datePeriod = OpenDatePeriod.between(previousEndDate.plusDays(1), previousEndDate.plusYears(1));
        } else {
            final int currentYear = LocalDate.now().getYear();
            datePeriod = OpenDatePeriod.between(LocalDate.of(currentYear, Month.JANUARY, 1), LocalDate.of(currentYear, Month.DECEMBER, 31));
        }
        return datePeriod;
    }

        @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<RestBookYear> create(@RequestBody final RestBookYear restBookYear) {
        final BookYear saved = bookRestFacade.create(restBookYear.toCore());
        logger.debug("created book year: " + saved);
        return new ResponseEntity<>(new RestBookYear(saved), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{bookYearId}")
    public void delete(@PathVariable("bookYearId") final UUID bookYearId) {
        bookRestFacade.delete(BookYearId.from(bookYearId));
    }

}
