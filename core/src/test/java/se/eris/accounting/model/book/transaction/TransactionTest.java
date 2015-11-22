package se.eris.accounting.model.book.transaction;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class TransactionTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void new_validateEmpty() {
        new Transaction(Optional.empty(), UUID.randomUUID(), LocalDate.now(), Collections.<TransactionLine>emptySet());
    }

    @Test
    public void new_validateBalanced() {
        final TransactionLine line1 = new TransactionLine(null, UUID.randomUUID(), new BigDecimal(120));
        final TransactionLine line2 = new TransactionLine(null, UUID.randomUUID(), new BigDecimal(-20));
        final TransactionLine line3 = new TransactionLine(null, UUID.randomUUID(), new BigDecimal(-100));
        final Collection<TransactionLine> lines = Arrays.asList(line1, line2, line3);
        new Transaction(Optional.empty(), UUID.randomUUID(), LocalDate.now(), lines);
    }

    @Test
    public void new_validateUnbalanced() {
        final TransactionLine line1 = new TransactionLine(null, UUID.randomUUID(), new BigDecimal(120.000001));
        final TransactionLine line2 = new TransactionLine(null, UUID.randomUUID(), new BigDecimal(-20));
        final TransactionLine line3 = new TransactionLine(null, UUID.randomUUID(), new BigDecimal(-100));
        final Collection<TransactionLine> lines = Arrays.asList(line1, line2, line3);

        exception.expect(NonZeroSumTransactionException.class);
        new Transaction(Optional.empty(), UUID.randomUUID(), LocalDate.now(), lines);
    }

}