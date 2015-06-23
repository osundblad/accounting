package se.eris.accounting.persistence.jpa.model;

import se.eris.accounting.model.Book;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "book")
public class JpaBook {

    @NotNull
    @Id
    @Column(nullable = false, length = 36)
    private String id;

    @NotNull
    @Column(nullable = false, length = 200)
    private String name;

    @NotNull
    @Column(nullable = false, length = 2000)
    private String description;


    @SuppressWarnings("UnusedDeclaration") // needed by Jpa framework
    public JpaBook() {
    }

    public JpaBook(@NotNull final Book book) {
        id = book.hasId() ? book.getId().toString() : UUID.randomUUID().toString();
        name = book.getName();
        description = book.getDescription();
    }

    @NotNull
    public Book toCore() {
        return new Book(UUID.fromString(id), name, description);
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final JpaBook jpaBook = (JpaBook) o;

        if (!id.equals(jpaBook.id)) return false;
        if (!name.equals(jpaBook.name)) return false;
        return description.equals(jpaBook.description);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }
}
