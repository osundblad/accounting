package se.eris.accounting.persistence.jpa.model;

import se.eris.accounting.model.book.BookYearId;
import se.eris.accounting.model.book.transaction.Transaction;
import se.eris.accounting.model.book.transaction.TransactionId;
import se.eris.accounting.model.book.transaction.TransactionLine;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "transaction")
public class JpaTransaction {

    @NotNull
    @Id
    @Column(nullable = false, length = 36)
    private String id;

    @NotNull
    @Column(name = "bookYearId", nullable = false, length = 36)
    private String bookYearId;

    @NotNull
    @Column(name = "transaction_date", nullable = false)
    private LocalDate date;

    @SuppressWarnings("UnusedDeclaration") // needed by Jpa framework
    public JpaTransaction() {
    }

    @SuppressWarnings("FeatureEnvy")
    public JpaTransaction(@NotNull final Transaction transaction) {
        id = transaction.getId().orElse(TransactionId.random()).asString();
        bookYearId = transaction.getBookYearId().asString();
        date = transaction.getDate();
    }

    @NotNull
    public Transaction toCore() {
        return Transaction.of(Optional.of(TransactionId.from(id)), BookYearId.from(bookYearId), date, getTransactionLines());
    }

    @NotNull
    private List<TransactionLine> getTransactionLines() {
        // todo
        return Collections.<TransactionLine>emptyList();
    }

    @SuppressWarnings({"SimplifiableIfStatement", "ControlFlowStatementWithoutBraces", "NonFinalFieldReferenceInEquals"})
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if ((o == null) || (getClass() != o.getClass())) return false;

        final JpaTransaction that = (JpaTransaction) o;

        if (!id.equals(that.id)) return false;
        if (!bookYearId.equals(that.bookYearId)) return false;
        return date.equals(that.date);
    }

    @SuppressWarnings("NonFinalFieldReferencedInHashCode")
    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = (31 * result) + bookYearId.hashCode();
        result = (31 * result) + date.hashCode();
        return result;
    }

}
