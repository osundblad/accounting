package se.eris.accounting.model.book;

import org.junit.Test;
import se.eris.type.OpenDatePeriod;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BookYearTest {

    @Test
    public void equals() {
        final LocalDate now = LocalDate.now();
        final BookId bookId = BookId.random();
        final BookYear bookYear1 = new BookYear(Optional.empty(), bookId, OpenDatePeriod.between(now, now));
        final BookYear bookYear2 = new BookYear(Optional.empty(), bookId, OpenDatePeriod.between(now, now));

        assertThat(bookYear1, is(bookYear1));
        assertThat(bookYear1, is(bookYear2));
    }

    @Test
    public void comparator_newToOld() {
        final LocalDate now = LocalDate.now();
        final BookId bookId = BookId.random();
        final BookYear bookYear1 = new BookYear(Optional.empty(), bookId, OpenDatePeriod.between(now, now));
        final BookYear bookYear2 = new BookYear(Optional.empty(), bookId, OpenDatePeriod.between(now.plusDays(1), now.plusDays(1)));
        final List<BookYear> bookYears = Arrays.asList(bookYear2, bookYear1);
        bookYears.sort(BookYear.NEW_TO_OLD);

        assertThat(bookYears, is(Arrays.asList(bookYear1, bookYear2)));
    }

}