package se.eris.accounting.model.book;

import org.jetbrains.annotations.NotNull;

public class Book {

    @NotNull
    private final BookId id;

    @NotNull
    private final String name;
    @NotNull
    private final String description;

    public Book(@NotNull final BookId bookId, @NotNull final String name, @NotNull final String description) {
        this.id = bookId;
        this.name = name;
        this.description = description;
    }

    @NotNull
    public BookId getId() {
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
