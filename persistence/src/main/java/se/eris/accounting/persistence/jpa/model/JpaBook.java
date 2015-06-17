package se.eris.accounting.persistence.jpa.model;

import se.eris.accounting.model.Book;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "book")
public class JpaBook {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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
        id = book.getIdRaw();
        name = book.getName();
        description = book.getDescription();
    }

    @NotNull
    public Book toCore() {
        return new Book(id, name, description);
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
