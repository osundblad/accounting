package se.eris.accounting.web.rest;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import se.eris.accounting.model.book.BookId;
import se.eris.accounting.model.book.BookYear;
import se.eris.accounting.model.book.DatePeriod;
import se.eris.accounting.services.BookRestFacade;
import se.eris.accounting.web.rest.model.RestBookYear;
import se.eris.accounting.web.rest.model.RestBookYearAccount;

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

    @NotNull
    private final BookRestFacade bookRestFacade;

    @Autowired
    public BookYearResource(@NotNull final BookRestFacade bookRestFacade) {
        this.bookRestFacade = bookRestFacade;
    }

    @NotNull
    @RequestMapping(method = RequestMethod.GET)
    public List<RestBookYear> getAll() {
        return bookRestFacade.getAllBookYears().map(RestBookYear::new).collect(Collectors.toList());
    }

    @NotNull
    @RequestMapping(method = RequestMethod.GET, value = "/{bookId}")
    public List<RestBookYear> get(@PathVariable("bookId") @NotNull final UUID bookId) {
        return bookRestFacade.getAllBookYears(bookId).map(RestBookYear::new).collect(Collectors.toList());
    }

    @NotNull
    @RequestMapping(method = RequestMethod.GET, value = "/{bookId}/template")
    public RestBookYear getTemplate(@PathVariable("bookId") @NotNull final UUID bookId) {
        final int year = LocalDate.now().getYear();
        return new RestBookYear(new BookYear(Optional.empty(), BookId.from(bookId), DatePeriod.between(LocalDate.of(year, Month.JANUARY, 1), LocalDate.of(year, Month.DECEMBER, Month.DECEMBER.maxLength()))));
    }

    @NotNull
    @RequestMapping(method = RequestMethod.GET, value = "/{bookId}/next")
    public RestBookYear getNext(@PathVariable("bookId") @NotNull final UUID bookId) {
        final Optional<BookYear> bookYear = bookRestFacade.getAllBookYears(bookId).sorted(BookYear.NEW_TO_OLD).findFirst();
        final DatePeriod datePeriod;
        if (bookYear.isPresent()) {
            final LocalDate previousEndDate = bookYear.get().getEndDate();
            datePeriod = DatePeriod.between(previousEndDate.plusDays(1), previousEndDate.plusYears(1));
        } else {
            final int currentYear = LocalDate.now().getYear();
            datePeriod = DatePeriod.between(LocalDate.of(currentYear, Month.JANUARY, 1), LocalDate.of(currentYear, Month.DECEMBER, 31));
        }
        return new RestBookYear(new BookYear(Optional.empty(), BookId.from(bookId), datePeriod));
    }

    @NotNull
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public RestBookYear create(@RequestBody @NotNull final RestBookYear restBookYear) {
        final BookYear saved = bookRestFacade.create(restBookYear.toCore());
        logger.debug("created book year: " + saved);
        return new RestBookYear(saved);
    }

    @NotNull
    @RequestMapping(method = RequestMethod.GET, value = "/{bookYear}/accounts")
    public List<RestBookYearAccount> getAccounts(@PathVariable("bookYear") @NotNull final UUID bookYear) {
        return bookRestFacade.getBookYearAccounts(bookYear).map(RestBookYearAccount::new).collect(Collectors.toList());
    }

}
