package se.eris.accounting.persistence.jpa.model;

import se.eris.accounting.model.book.BookYearId;
import se.eris.accounting.model.book.transaction.Transaction;
import se.eris.accounting.model.book.transaction.TransactionId;
import se.eris.accounting.model.book.transaction.TransactionLine;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "transaction")
public class JpaTransaction {

        @Id
    @Column(nullable = false, length = 36)
    private String id;

        @Column(name = "bookYearId", nullable = false, length = 36)
    private String bookYearId;

        @Column(name = "transaction_date", nullable = false)
    private LocalDate date;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transaction")
    private Collection<JpaTransactionLine> transactionLines;

    @SuppressWarnings("UnusedDeclaration") // needed by Jpa framework
    public JpaTransaction() {
    }

    @SuppressWarnings("FeatureEnvy")
    public JpaTransaction(final Transaction transaction) {
        id = transaction.getId().orElse(TransactionId.random()).asString();
        bookYearId = transaction.getBookYearId().asString();
        date = transaction.getDate();
        transactionLines = getJpaTransactionLines(transaction.getTransactionLines());
    }

        private Collection<JpaTransactionLine> getJpaTransactionLines(final Stream<TransactionLine> transactionLines) {
        return transactionLines.map(tl -> new JpaTransactionLine(tl, this)).collect(Collectors.toList());
    }

        public Transaction toCore() {
        return Transaction.of(Optional.of(TransactionId.from(id)), BookYearId.from(bookYearId), date, getTransactionLines());
    }

        private Collection<TransactionLine> getTransactionLines() {
        return transactionLines.stream().map(JpaTransactionLine::toCore).collect(Collectors.toList());
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
