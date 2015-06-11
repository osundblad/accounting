package se.eris.accounting.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Book {

    @Nullable
    private final Long id;

    @NotNull
    private final String name;
    @NotNull
    private final String description;

    public Book(@Nullable final Long id, @NotNull final String name, @NotNull final String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    @NotNull
    public String getName() {
        return name;
    }

    @NotNull
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Book{id=" + id + ", name='" + name + '\'' + ", description='" + description + '\'' + '}';
    }
}
