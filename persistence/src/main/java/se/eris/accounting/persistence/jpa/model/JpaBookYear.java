package se.eris.accounting.persistence.jpa.model;

import se.eris.accounting.model.BookYear;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "bookYear")
public class JpaBookYear {

    @NotNull
    @Id
    @Column(nullable = false, length = 36)
    private String id;

    @NotNull
    @Column(name = "bookId", nullable = false, length = 36)
    private String bookId;

    @NotNull
    @Column(name = "fromDate", nullable = false)
    private LocalDate fromDate;

    @NotNull
    @Column(name = "toDate", nullable = false)
    private LocalDate toDate;


    @SuppressWarnings("UnusedDeclaration") // needed by Jpa framework
    public JpaBookYear() {
    }

    public JpaBookYear(@NotNull final BookYear bookYear) {
        id = bookYear.hasId() ? bookYear.getId().toString() : UUID.randomUUID().toString();
        bookId = bookYear.getBookId().toString();
        fromDate = bookYear.getStartDate();
        toDate = bookYear.getEndDate();
    }

    @NotNull
    public BookYear toCore() {
        return new BookYear(UUID.fromString(id), UUID.fromString(bookId), fromDate, toDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JpaBookYear that = (JpaBookYear) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (!bookId.equals(that.bookId)) return false;
        if (!fromDate.equals(that.fromDate)) return false;
        if (!toDate.equals(that.toDate)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + bookId.hashCode();
        result = 31 * result + fromDate.hashCode();
        result = 31 * result + toDate.hashCode();
        return result;
    }

}
