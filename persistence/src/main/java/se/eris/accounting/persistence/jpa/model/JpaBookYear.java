package se.eris.accounting.persistence.jpa.model;

import se.eris.accounting.model.BookYear;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "bookYear")
public class JpaBookYear {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column
    private long bookId;

    @NotNull
    @Column(nullable = false)
    private LocalDate start;

    @NotNull
    @Column(nullable = false)
    private LocalDate end;


    @SuppressWarnings("UnusedDeclaration") // needed by Jpa framework
    public JpaBookYear() {
    }

    public JpaBookYear(@NotNull final BookYear bookYear) {
        id = bookYear.getIdRaw();
        bookId = bookYear.getBookId();
        start = bookYear.getStartDate();
        end = bookYear.getEndDate();
    }

    @NotNull
    public BookYear toCore() {
        return new BookYear(id, bookId, start, end);
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final JpaBookYear that = (JpaBookYear) o;

        if (bookId != that.bookId) return false;
        if (!id.equals(that.id)) return false;
        if (!start.equals(that.start)) return false;
        return end.equals(that.end);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (int) (bookId ^ (bookId >>> 32));
        result = 31 * result + start.hashCode();
        result = 31 * result + end.hashCode();
        return result;
    }
}
