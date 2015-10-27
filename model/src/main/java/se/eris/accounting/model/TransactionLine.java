package se.eris.accounting.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.util.UUID;

public class TransactionLine {

    @Nullable
    private final UUID id;

    @NotNull
    private final UUID bookYearAccountId;
    @NotNull
    private final Amount amount;

    public TransactionLine(@Nullable final UUID id, @NotNull final UUID bookYearAccountId, @NotNull final BigDecimal amount) {
        this.id = id;
        this.bookYearAccountId = bookYearAccountId;
        this.amount = Amount.of(amount);
    }

    @NotNull
    public Amount getAmount() {
        return amount;
    }

    @SuppressWarnings({"ControlFlowStatementWithoutBraces", "SimplifiableIfStatement"})
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if ((o == null) || (getClass() != o.getClass())) return false;

        final TransactionLine that = (TransactionLine) o;

        if ((id != null) ? !id.equals(that.id) : (that.id != null)) return false;
        if (!bookYearAccountId.equals(that.bookYearAccountId)) return false;
        return amount.equals(that.amount);
    }

    @Override
    public int hashCode() {
        int result = (id != null) ? id.hashCode() : 0;
        result = (31 * result) + bookYearAccountId.hashCode();
        result = (31 * result) + amount.hashCode();
        return result;
    }

}
