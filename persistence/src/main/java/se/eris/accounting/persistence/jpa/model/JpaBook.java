package se.eris.accounting.persistence.jpa.model;

import se.eris.accounting.model.book.Book;
import se.eris.accounting.model.book.BookId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Entity
@Table(name = "book")
public class JpaBook {

    public static final int NAME_MAX_LENGTH = 200;
    public static final int DESCRIPTION_MAX_LENGTH = 2000;

    @NotNull
    @Id
    @Column(nullable = false, length = 36)
    private String id;

    @NotNull
    @Column(nullable = false, length = NAME_MAX_LENGTH)
    private String name;

    @NotNull
    @Column(nullable = false, length = DESCRIPTION_MAX_LENGTH)
    private String description;


    @SuppressWarnings("UnusedDeclaration") // needed by Jpa framework
    public JpaBook() {
    }

    @SuppressWarnings("FeatureEnvy")
    public JpaBook(@NotNull final Book book) {
        id = book.getId().orElse(BookId.random()).asString();
        name = book.getName();
        description = book.getDescription();
    }

    @NotNull
    public Book toCore() {
        return new Book(Optional.of(BookId.from(id)), name, description);
    }

    @SuppressWarnings({"SimplifiableIfStatement", "ControlFlowStatementWithoutBraces", "NonFinalFieldReferenceInEquals"})
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if ((o == null) || (getClass() != o.getClass())) return false;

        final JpaBook jpaBook = (JpaBook) o;

        if (!id.equals(jpaBook.id)) return false;
        if (!name.equals(jpaBook.name)) return false;
        return description.equals(jpaBook.description);
    }

    @SuppressWarnings("NonFinalFieldReferencedInHashCode")
    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = (31 * result) + name.hashCode();
        result = (31 * result) + description.hashCode();
        return result;
    }
}
