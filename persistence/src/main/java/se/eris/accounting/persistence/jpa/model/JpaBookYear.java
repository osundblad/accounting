package se.eris.accounting.persistence.jpa.model;

import se.eris.accounting.model.book.BookId;
import se.eris.accounting.model.book.BookYear;
import se.eris.accounting.model.book.BookYearId;
import se.eris.jtype.type.OpenDatePeriod;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Entity
@Table(name = "book_year")
public class JpaBookYear {

        @Id
    @Column(nullable = false, length = 36)
    private String id;

        @Column(name = "bookId", nullable = false, length = 36)
    private UUID bookId;

        @Column(name = "fromDate", nullable = false)
    private LocalDate fromDate;

        @Column(name = "toDate", nullable = false)
    private LocalDate toDate;


    @SuppressWarnings("UnusedDeclaration") // needed by Jpa framework
    public JpaBookYear() {
    }

    @SuppressWarnings("FeatureEnvy")
    public JpaBookYear(final BookYear bookYear) {
        id = bookYear.getId().orElse(BookYearId.random()).asString();
        bookId = bookYear.getBookId().raw();
        fromDate = bookYear.getStartDate();
        toDate = bookYear.getEndDate();
    }

        public BookYear toCore() {
        return new BookYear(Optional.of(BookYearId.from(id)), BookId.from(bookId), OpenDatePeriod.between(fromDate, toDate));
    }

    @SuppressWarnings({"RedundantIfStatement", "ControlFlowStatementWithoutBraces", "NonFinalFieldReferenceInEquals"})
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if ((o == null) || (getClass() != o.getClass())) return false;

        final JpaBookYear that = (JpaBookYear) o;

        if ((id != null) ? !id.equals(that.id) : (that.id != null)) return false;
        if (!bookId.equals(that.bookId)) return false;
        if (!fromDate.equals(that.fromDate)) return false;
        if (!toDate.equals(that.toDate)) return false;

        return true;
    }

    @SuppressWarnings("NonFinalFieldReferencedInHashCode")
    @Override
    public int hashCode() {
        int result = (id != null) ? id.hashCode() : 0;
        result = (37 * result) + bookId.hashCode();
        result = (37 * result) + fromDate.hashCode();
        result = (37 * result) + toDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "JpaBookYear{" +
                "id='" + id + '\'' +
                ", bookId='" + bookId + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }

}
