package se.eris.accounting.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.UUID;

public class BookYearAccount {

    @Nullable
    private final UUID id;

    @NotNull
    private final UUID bookYearId;

    public BookYearAccount(@Nullable final UUID id, @NotNull final UUID bookYearId) {
        this.id = id;
        this.bookYearId = bookYearId;
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
    public UUID getBookYearId() {
        return bookYearId;
    }


}
