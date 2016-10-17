package se.eris.accounting.web.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;
import se.eris.accounting.model.book.BookYearId;
import se.eris.accounting.model.book.transaction.Transaction;
import se.eris.accounting.model.book.transaction.TransactionId;
import se.eris.accounting.model.book.transaction.TransactionLine;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RestTransaction {

    @SuppressWarnings("FeatureEnvy")
    public static RestTransaction of(final Transaction transaction) {
        return new RestTransaction(
                transaction.getId().map(TransactionId::asUUID).orElse(null),
                transaction.getBookYearId().asUUID(),
                transaction.getTransactionLines().map(RestTransactionLine::of).collect(Collectors.toList())
        );
    }

    @JsonProperty
    @Nullable
    private final UUID id;

    @JsonProperty
        private final UUID bookYearId;

    @JsonProperty
        private final List<RestTransactionLine> transactionLines;

    @JsonCreator
    public RestTransaction(
            @JsonProperty("id") @Nullable final UUID id,
            @JsonProperty("bookYearId") final UUID bookYearId,
            @JsonProperty("transactionLines") final List<RestTransactionLine> transactionLines) {
        this.id = id;
        this.bookYearId = bookYearId;
        this.transactionLines = transactionLines;
    }

        public Transaction toCore() {
        return Transaction.of(getId(), BookYearId.from(bookYearId), LocalDate.now(), getTransactionLines().collect(Collectors.toList()));
    }

    @JsonIgnore
        private Stream<TransactionLine> getTransactionLines() {
        return transactionLines.stream().map(RestTransactionLine::toCore);
    }

    @JsonIgnore
        public Optional<TransactionId> getId() {
        return Optional.ofNullable(id).map(TransactionId::from);
    }

    @Override
    public String toString() {
        return "RestTransaction{" +
                "id=" + id +
                ", bookYearId=" + bookYearId +
                ", transactionLines=" + transactionLines +
                '}';
    }

}
