package se.eris.accounting.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.UUID;

public class BookYear {

    public static final Comparator<? super BookYear> NEW_TO_OLD = new Comparator<BookYear>() {
        @Override
        public int compare(BookYear o1, BookYear o2) {
            return o1.endDate.compareTo(o2.endDate);
        }
    };

    @Nullable
    private final UUID id;

    @NotNull
    private final UUID bookId;
    @NotNull
    private final LocalDate startDate;
    @NotNull
    private final LocalDate endDate;

    public BookYear(@Nullable final UUID id, @NotNull final UUID bookId, @NotNull final LocalDate startDate, @NotNull final LocalDate endDate) {
        this.id = id;
        this.bookId = bookId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean hasId() {
        return id != null;
    }

    @NotNull
    public UUID getId() {
        if (id == null) {
            throw new NotPersistedException(this);
        }
        return id;
    }

    @Nullable
    public UUID getIdRaw() {
        return id;
    }

    @NotNull
    public UUID getBookId() {
        return bookId;
    }

    @NotNull
    public LocalDate getStartDate() {
        return startDate;
    }

    @NotNull
    public LocalDate getEndDate() {
        return endDate;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookYear bookYear = (BookYear) o;

        if (id != null ? !id.equals(bookYear.id) : bookYear.id != null) return false;
        if (!bookId.equals(bookYear.bookId)) return false;
        if (!startDate.equals(bookYear.startDate)) return false;
        return endDate.equals(bookYear.endDate);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + bookId.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "BookYear{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
