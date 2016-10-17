package se.eris.accounting.model.book;

import se.eris.jtype.type.OpenDatePeriod;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Optional;

public final class BookYear {

    public static final Comparator<BookYear> NEW_TO_OLD = (o1, o2) -> o1.getStartDate().compareTo(o2.getStartDate());

    private final Optional<BookYearId> id;
    
    private final BookId bookId;
    private final OpenDatePeriod datePeriod;

    public BookYear(final Optional<BookYearId> id, final BookId bookId, final OpenDatePeriod datePeriod) {
        this.id = id;
        this.bookId = bookId;
        this.datePeriod = datePeriod;
    }

    public Optional<BookYearId> getId() {
        return id;
    }

    public BookId getBookId() {
        return bookId;
    }

    public LocalDate getStartDate() {
        return datePeriod.getStartDate();
    }

    public LocalDate getEndDate() {
        return datePeriod.getEndDate();
    }

    @SuppressWarnings({"SimplifiableIfStatement", "ControlFlowStatementWithoutBraces"})
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if ((o == null) || (getClass() != o.getClass())) return false;

        final BookYear bookYear = (BookYear) o;

        if (!id.equals(bookYear.id)) return false;
        if (!bookId.equals(bookYear.bookId)) return false;
        return datePeriod.equals(bookYear.datePeriod);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = (31 * result) + bookId.hashCode();
        result = (31 * result) + datePeriod.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "BookYear{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", datePeriod=" + datePeriod +
                '}';
    }

}
