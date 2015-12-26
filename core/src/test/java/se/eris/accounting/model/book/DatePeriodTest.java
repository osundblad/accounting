package se.eris.accounting.model.book;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;

public class DatePeriodTest {

    public static final int YEAR_2000 = 2000;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void between_valid() {
        DatePeriod.between(LocalDate.of(YEAR_2000, 1, 1), LocalDate.of(YEAR_2000 + 1, 1, 1));
    }

    @Test
    public void between_endDateBeforeStartDate_shouldFail() {
        exception.expect(IllegalArgumentException.class);
        DatePeriod.between(LocalDate.of(YEAR_2000 + 1, 1, 1), LocalDate.of(YEAR_2000, 1, 1));
    }

}