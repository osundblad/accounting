package se.eris.accounting.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;

public class BookYear {

    @Nullable
    private final Long id;

    private final long bookId;
    @NotNull
    private final LocalDate startDate;
    @NotNull
    private final LocalDate endDate;

    public BookYear(@Nullable final Long id, @NotNull final Long bookId, @NotNull final LocalDate startDate, @NotNull final LocalDate endDate) {
        this.id = id;
        this.bookId = bookId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean hasId() {
        return id != null;
    }

    public long getId() {
        if (id == null) {
            throw new NotPersistedException(this);
        }
        return id;
    }

    @Nullable
    public Long getIdRaw() {
        return id;
    }

    public long getBookId() {
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
