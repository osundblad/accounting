package se.eris.accounting.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;

public class BookYear {

    @Nullable
    private Long id;

    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;

    public BookYear(@Nullable final Long id, @NotNull final LocalDate startDate, @NotNull final LocalDate endDate) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean hasId() {
        return id != null;
    }

    @Nullable
    public Long getId() {
        if (id == null) {
            throw new NotPersistedException("BookYear not persisted");
        }
        return id;
    }

    @Nullable
    public Long getIdRaw() {
        return id;
    }

    @NotNull
    public LocalDate getStartDate() {
        return startDate;
    }

    @NotNull
    public LocalDate getEndDate() {
        return endDate;
    }
}
