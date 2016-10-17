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
import se.eris.accounting.model.book.BookYearId;
import se.eris.accounting.model.book.account.AccountClass;
import se.eris.accounting.model.book.transaction.Amount;
import se.eris.accounting.model.book.transaction.Transaction;
import se.eris.accounting.model.book.transaction.TransactionLine;
import se.eris.accounting.web.rest.model.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/test", produces = MediaType.APPLICATION_JSON_VALUE)
public class TestResource {

    private static final Logger logger = LoggerFactory.getLogger(TestResource.class);

        private final BookResource bookResource;

        private final BookYearResource bookYearResource;

        private final AccountResource accountResource;

        private final TransactionResource transactionResource;

    @Autowired
    public TestResource(@NotNull final BookResource bookResource, @NotNull final BookYearResource bookYearResource, @NotNull final AccountResource accountResource, @NotNull final TransactionResource transactionResource) {
        this.bookResource = bookResource;
        this.bookYearResource = bookYearResource;
        this.accountResource = accountResource;
        this.transactionResource = transactionResource;
    }

    @SuppressWarnings("FeatureEnvy")
        @RequestMapping(method = RequestMethod.GET, value = "/run/{bookName}")
    public ResponseEntity<String> run(@PathVariable("bookName") final String bookName) {
        logger.info("creating book");
        final RestBook book = bookResource.create(new RestBook(new Book(Optional.empty(), BookName.of(bookName), BookDescription.of("This book was created by the test script.")))).getBody();
        logger.info("  created book: '" + bookName + "'");

        final RestBookYear bookYear1 = bookYearResource.create(new RestBookYear(null, book.getBookId().get().asUUID(), "2014-07-01", "2015-06-30")).getBody();
        logger.info("  created year: '" + bookYear1.toCore().toString() + "'");
        final RestBookYear bookYear2 = bookYearResource.create(bookYearResource.getNext(book.getBookId().get().asUUID()).getBody()).getBody();
        logger.info("  created year: '" + bookYear2.toCore().toString() + "'");

        final UUID bookYear1Id = bookYear1.getId().get();
        final RestBookYearAccount kassa = accountResource.create(new RestBookYearAccount(null, bookYear1Id, new RestAccountInfo(AccountClass.ASSET, "1920", "Kassa", "B9")));
        final RestBookYearAccount bank1 = accountResource.create(new RestBookYearAccount(null, bookYear1Id, new RestAccountInfo(AccountClass.ASSET, "1940", "Avanza - Depå", "B9 Strö pengar")));
        final RestBookYearAccount bank2 = accountResource.create(new RestBookYearAccount(null, bookYear1Id, new RestAccountInfo(AccountClass.ASSET, "1941", "Avanza - Klarna+", "B9 Lite ränta")));
        final RestBookYearAccount bank3 = accountResource.create(new RestBookYearAccount(null, bookYear1Id, new RestAccountInfo(AccountClass.ASSET, "1942", "Avanza - Klarna+, 12 månader", "B9 Lite mer ränta")));

        final RestBookYearAccount egetKapital1 = accountResource.create(new RestBookYearAccount(null, bookYear1Id, new RestAccountInfo(AccountClass.LIABILITY, "2010", "Eget kapital, delägare 1", "B10")));
        final RestBookYearAccount egetKapital2 = accountResource.create(new RestBookYearAccount(null, bookYear1Id, new RestAccountInfo(AccountClass.LIABILITY, "2020", "Eget kapital, delägare 2", "B10")));
        final RestBookYearAccount momsUt = accountResource.create(new RestBookYearAccount(null, bookYear1Id, new RestAccountInfo(AccountClass.LIABILITY, "2610", "Utgående moms, 25 %", "B14")));
        final RestBookYearAccount momsIn = accountResource.create(new RestBookYearAccount(null, bookYear1Id, new RestAccountInfo(AccountClass.LIABILITY, "2640", "Ingående moms", "B14")));

        final RestBookYearAccount inkomst = accountResource.create(new RestBookYearAccount(null, bookYear1Id, new RestAccountInfo(AccountClass.INCOME, "3000", "Försäljning", "R1 Försäljning och utfört arbete samt övriga momspliktiga intäkter")));
        final RestBookYearAccount rätor = accountResource.create(new RestBookYearAccount(null, bookYear1Id, new RestAccountInfo(AccountClass.INCOME, "3100", "Momsfria intäkter", "R2")));
        final RestBookYearAccount varor = accountResource.create(new RestBookYearAccount(null, bookYear1Id, new RestAccountInfo(AccountClass.EXPENSE, "4000", "Varor", "R5")));
        final RestBookYearAccount tele = accountResource.create(new RestBookYearAccount(null, bookYear1Id, new RestAccountInfo(AccountClass.EXPENSE, "6200", "Tele och post", "R6")));

        transactionResource.create(RestTransaction.of(
                createNewTransaction(bookYear1,
                        createNewTransactionLine(egetKapital1, "50100"),
                        createNewTransactionLine(bank1, "-50100"))));
        transactionResource.create(RestTransaction.of(
                createNewTransaction(bookYear1,
                        createNewTransactionLine(egetKapital2, "49900"),
                        createNewTransactionLine(bank1, "-49900"))));
        transactionResource.create(RestTransaction.of(
                createNewTransaction(bookYear1,
                        createNewTransactionLine(inkomst, "1200"),
                        createNewTransactionLine(bank1, "-800"),
                        createNewTransactionLine(kassa, "-400"))));

        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

        private TransactionLine createNewTransactionLine(@NotNull final RestBookYearAccount account, @NotNull final String amount) {
        return TransactionLine.of(Optional.empty(), account.getId().get(), Amount.of(amount));
    }

        private Transaction createNewTransaction(@NotNull final RestBookYear year, @NotNull final TransactionLine... transactionLines) {
        return Transaction.of(Optional.empty(), BookYearId.from(year.getId().get()), LocalDate.now(), Arrays.asList(transactionLines));
    }

        @RequestMapping(method = RequestMethod.GET, value = "/delete/{bookName}")
    public ResponseEntity<String> delete(@PathVariable("bookName") final String bookName) {
        final Optional<Book> book = getBookByName(bookName);
        if (book.isPresent()) {
            logger.info("deleting book: '" + bookName + "'");
            deleteAllBookYears(book.get());
            bookResource.delete(book.flatMap(Book::getId).get().asUUID());
            logger.info("  deleted book: '" + bookName + "'");
            return new ResponseEntity<>("deleted", HttpStatus.OK);
        } else {
            logger.info("deleting book: '" + bookName + "' not found");
            return new ResponseEntity<>("'" + bookName + "' not found", HttpStatus.NOT_FOUND);
        }
    }

    private void deleteAllBookYears(@NotNull final Book book) {
        for (final RestBookYear year : bookYearResource.get(book.getId().get().asUUID())) {
            logger.info("  deleting year: '" + year.toString() + "'");
            deleteAllTransactions(year);
            deleteAllAccounts(year);
            bookYearResource.delete(year.getId().get());
        }
    }

    private void deleteAllTransactions(@NotNull final RestBookYear year) {
        for (final RestTransaction transaction : transactionResource.getForYear(year.getId().get()).getBody()) {
            logger.info("  deleting transaction: '" + transaction.toString() + "'");
            transactionResource.delete(transaction.getId().get().asUUID());
        }
    }

    private void deleteAllAccounts(@NotNull final RestBookYear year) {
        for (final RestBookYearAccount account : accountResource.get(year.getId().get())) {
            logger.info("  deleting account: '" + account.toString() + "'");
            accountResource.delete(account.getId().get().asUUID());
        }
    }

        private Optional<Book> getBookByName(@NotNull final String bookName) {
        return bookResource.getAll().stream().map(RestBook::toCore).filter(b -> b.getName().equals(BookName.of(bookName))).findFirst();
    }

}
