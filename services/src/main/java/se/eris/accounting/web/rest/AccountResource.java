package se.eris.accounting.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import se.eris.accounting.model.book.BookYearId;
import se.eris.accounting.model.book.account.BookYearAccountId;
import se.eris.accounting.services.BookRestFacade;
import se.eris.accounting.web.rest.model.RestBookYearAccount;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/account", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountResource {

    private final BookRestFacade bookRestFacade;

    @Autowired
    public AccountResource(final BookRestFacade bookRestFacade) {
        this.bookRestFacade = bookRestFacade;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{bookYearId}")
    public List<RestBookYearAccount> get(@PathVariable("bookYearId") final UUID bookYearId) {
        return bookRestFacade.getBookYearAccounts(BookYearId.from(bookYearId)).map(RestBookYearAccount::of).collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.POST)
    public RestBookYearAccount create(@RequestBody final RestBookYearAccount account) {
        return RestBookYearAccount.of(bookRestFacade.create(account.toCore()));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{accountId}")
    public void delete(@PathVariable("accountId") final UUID bookYearAccountId) {
        bookRestFacade.delete(BookYearAccountId.from(bookYearAccountId));
    }

}
