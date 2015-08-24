package se.eris.accounting.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;
import java.util.UUID;

public class BookYear {

    @Nullable
    private final UUID id;

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
