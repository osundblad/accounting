package se.eris.accounting.model.book;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class Book {

    @NotNull
    private final Optional<BookId> id;

    @NotNull
    private final BookName name;
    @NotNull
    private final BookDescription description;

    public Book(@NotNull final Optional<BookId> id, @NotNull final BookName name, @NotNull final BookDescription description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @NotNull
    public Optional<BookId> getId() {
        return id;
    }

    @NotNull
    public BookName getName() {
        return name;
    }

    @NotNull
    public BookDescription getDescription() {
        return description;
    }

    @SuppressWarnings({"ControlFlowStatementWithoutBraces", "SimplifiableIfStatement"})
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if ((o == null) || (getClass() != o.getClass())) return false;

        final Book book = (Book) o;

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
