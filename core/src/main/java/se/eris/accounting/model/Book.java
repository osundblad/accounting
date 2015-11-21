package se.eris.accounting.model;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;

public class Book {

    @NotNull
    private final Optional<UUID> id;

    @NotNull
    private final String name;
    @NotNull
    private final String description;

    public Book(@NotNull final Optional<UUID> id, @NotNull final String name, @NotNull final String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public boolean hasId() {
        return id.isPresent();
    }

    @NotNull
    public Optional<UUID> getId() {
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

    @SuppressWarnings("ControlFlowStatementWithoutBraces")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (!id.equals(book.id)) return false;
        if (!name.equals(book.name)) return false;
        return description.equals(book.description);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = (31 * result) + name.hashCode();
        result = (31 * result) + description.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Book{id=" + id + ", name='" + name + '\'' + ", description='" + description + '\'' + '}';
    }

}
