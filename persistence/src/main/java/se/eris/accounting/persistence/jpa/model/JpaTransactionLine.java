package se.eris.accounting.persistence.jpa.model;

import se.eris.accounting.model.book.account.BookYearAccountId;
import se.eris.accounting.model.book.transaction.Amount;
import se.eris.accounting.model.book.transaction.TransactionLine;
import se.eris.accounting.model.book.transaction.TransactionLineId;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "transaction_line")
public class JpaTransactionLine {

        @Id
    @Column(nullable = false, length = 36)
    private String id;

        @ManyToOne(optional = false)
    @JoinColumn(name = "transaction_id", referencedColumnName = "id", nullable = false)
    private JpaTransaction transaction;

        @Column(name = "accountId", nullable = false, length = 36)
    private String accountId;

        @Column(name = "amount", nullable = false, length = 36)
    private String amount;

    @SuppressWarnings("UnusedDeclaration") // needed by Jpa framework
    public JpaTransactionLine() {
    }

    public JpaTransactionLine(final TransactionLine transactionLine, final JpaTransaction jpaTransaction) {
        id = transactionLine.getId().orElse(TransactionLineId.random()).asString();
        transaction = jpaTransaction;
        accountId = transactionLine.getBookYearAccountId().asString();
        amount = transactionLine.getAmount().asString();
    }

        public TransactionLine toCore() {
        return TransactionLine.of(Optional.of(TransactionLineId.from(id)), BookYearAccountId.from(accountId), Amount.of(amount));
    }

    @SuppressWarnings({"SimplifiableIfStatement", "ControlFlowStatementWithoutBraces", "NonFinalFieldReferenceInEquals"})
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if ((o == null) || (getClass() != o.getClass())) return false;

        final JpaTransactionLine that = (JpaTransactionLine) o;

        if (!id.equals(that.id)) return false;
        if (!transaction.equals(that.transaction)) return false;
        if (!accountId.equals(that.accountId)) return false;
        return amount.equals(that.amount);
    }

    @SuppressWarnings("NonFinalFieldReferencedInHashCode")
    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = (31 * result) + transaction.hashCode();
        result = (31 * result) + accountId.hashCode();
        result = (31 * result) + amount.hashCode();
        return result;
    }
}
