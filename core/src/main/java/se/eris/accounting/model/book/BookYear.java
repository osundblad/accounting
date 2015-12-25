package se.eris.accounting.model.book;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Optional;

public final class BookYear {

    @NotNull
    public static final Comparator<BookYear> NEW_TO_OLD = (o1, o2) -> o1.getStartDate().compareTo(o2.getStartDate());

    @NotNull
    private final Optional<BookYearId> id;
    @NotNull
    private final BookId bookId;
    @NotNull
    private final DatePeriod datePeriod;

    public BookYear(@NotNull final Optional<BookYearId> id, @NotNull final BookId bookId, @NotNull final DatePeriod datePeriod) {
        this.id = id;
        this.bookId = bookId;
        this.datePeriod = datePeriod;
    }

    @NotNull
    public Optional<BookYearId> getId() {
        return id;
    }

    @NotNull
    public BookId getBookId() {
        return bookId;
    }

    @NotNull
    public LocalDate getStartDate() {
        return datePeriod.getStartDate();
    }

    @NotNull
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
