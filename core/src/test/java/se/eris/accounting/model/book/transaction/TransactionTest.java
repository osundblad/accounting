package se.eris.accounting.model.book.transaction;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import se.eris.accounting.model.book.BookYearId;
import se.eris.accounting.model.book.account.BookYearAccountId;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public class TransactionTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    private static final BookYearId BOOK_YEAR_ID = BookYearId.random();

    @Test
    public void new_validateEmpty() {
        Transaction.of(Optional.empty(), BOOK_YEAR_ID, LocalDate.now(), Collections.<TransactionLine>emptySet());
    }

    @Test
    public void new_validateBalanced() {
        final TransactionLine line1 = new TransactionLine(TransactionLineId.random(), BookYearAccountId.random(), Amount.of(120));
        final TransactionLine line2 = new TransactionLine(TransactionLineId.random(), BookYearAccountId.random(), Amount.of(-20));
        final TransactionLine line3 = new TransactionLine(TransactionLineId.random(), BookYearAccountId.random(), Amount.of(-100));
        final Collection<TransactionLine> lines = Arrays.asList(line1, line2, line3);

        Transaction.of(Optional.empty(), BOOK_YEAR_ID, LocalDate.now(), lines);
    }

    @Test
    public void new_validateUnbalanced() {
        final TransactionLine line1 = new TransactionLine(null, BookYearAccountId.random(), Amount.of("120.01"));
        final TransactionLine line2 = new TransactionLine(null, BookYearAccountId.random(), Amount.of(-20));
        final TransactionLine line3 = new TransactionLine(null, BookYearAccountId.random(), Amount.of(-100));
        final Collection<TransactionLine> lines = Arrays.asList(line1, line2, line3);

        exception.expect(NonZeroSumTransactionException.class);
        Transaction.of(Optional.empty(), BOOK_YEAR_ID, LocalDate.now(), lines);
    }

}