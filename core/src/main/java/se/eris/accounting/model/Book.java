package se.eris.accounting.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class Book {

    @Nullable
    private final UUID id;

    @NotNull
    private final String name;
    @NotNull
    private final String description;

    public Book(@Nullable final UUID id, @NotNull final String name, @NotNull final String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    @NotNull
    public String getName() {
        return name;
    }

    @NotNull
    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (id != null ? !id.equals(book.id) : book.id != null) return false;
        if (!name.equals(book.name)) return false;
        if (!description.equals(book.description)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Book{id=" + id + ", name='" + name + '\'' + ", description='" + description + '\'' + '}';
    }

}
