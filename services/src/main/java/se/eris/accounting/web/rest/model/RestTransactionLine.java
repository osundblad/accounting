package se.eris.accounting.web.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import se.eris.accounting.model.book.account.BookYearAccountId;
import se.eris.accounting.model.book.transaction.Amount;
import se.eris.accounting.model.book.transaction.TransactionLine;
import se.eris.accounting.model.book.transaction.TransactionLineId;

import java.util.Optional;
import java.util.UUID;

public class RestTransactionLine {

    @SuppressWarnings("FeatureEnvy")
    public static RestTransactionLine of(@NotNull final TransactionLine transactionLine) {
        return new RestTransactionLine(
                transactionLine.getId().map(TransactionLineId::asUUID).orElse(null),
                transactionLine.getBookYearAccountId().asUUID(),
                transactionLine.getAmount().asString()
        );
    }

    @JsonProperty
    @Nullable
    private final UUID id;

    @JsonProperty
        private final UUID accountId;

    @JsonProperty
        private final String amount;

    @JsonCreator
    public RestTransactionLine(
            @JsonProperty("id") @Nullable final UUID id,
            @JsonProperty("accountId") @NotNull final UUID accountId,
            @JsonProperty("amount") @NotNull final String amount) {
        this.id = id;
        this.accountId = accountId;
        this.amount = amount;
    }

    @JsonIgnore
        private Optional<TransactionLineId> getId() {
        return Optional.ofNullable(id).map(TransactionLineId::from);
    }

        public TransactionLine toCore() {
        return TransactionLine.of(getId(), BookYearAccountId.from(accountId), Amount.of(amount));
    }

}
