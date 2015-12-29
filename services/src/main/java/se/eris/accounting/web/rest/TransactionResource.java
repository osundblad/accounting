package se.eris.accounting.web.rest;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.eris.accounting.model.book.BookYearId;
import se.eris.accounting.model.book.transaction.Transaction;
import se.eris.accounting.model.book.transaction.TransactionId;
import se.eris.accounting.services.BookRestFacade;
import se.eris.accounting.web.rest.model.RestTransaction;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(value = "/api/transaction", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionResource {

    @NotNull
    private final BookRestFacade bookRestFacade;

    @Autowired
    public TransactionResource(@NotNull final BookRestFacade bookRestFacade) {
        this.bookRestFacade = bookRestFacade;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{bookYearId}")
    public ResponseEntity<List<RestTransaction>> get(@PathVariable("bookYearId") @NotNull final UUID bookYearId) {
        final Stream<Transaction> transactions = bookRestFacade.getTransactions(BookYearId.from(bookYearId));
        final List<RestTransaction> restTransactions = transactions.map(RestTransaction::of).collect(Collectors.toList());
        return new ResponseEntity<>(restTransactions, HttpStatus.OK);
    }

    @NotNull
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<RestTransaction> create(@RequestBody @NotNull final RestTransaction restTransaction) {
        return new ResponseEntity<>(RestTransaction.of(bookRestFacade.create(restTransaction.toCore())), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{transactionId}")
    public void deleteAccount(@PathVariable("transactionId") @NotNull final UUID transactionId) {
        bookRestFacade.delete(TransactionId.from(transactionId));
    }

}
