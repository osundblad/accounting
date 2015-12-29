package se.eris.accounting.web.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import se.eris.accounting.model.book.BookYearId;
import se.eris.accounting.model.book.transaction.Transaction;
import se.eris.accounting.model.book.transaction.TransactionId;
import se.eris.accounting.model.book.transaction.TransactionLine;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

public class RestTransaction {

    @JsonProperty
    @Nullable
    private final UUID id;
    @JsonProperty
    @NotNull
    private final UUID bookYearId;

    @SuppressWarnings("FeatureEnvy")
    public static RestTransaction of(@NotNull final Transaction transaction) {
        return new RestTransaction(
                transaction.getId().map(TransactionId::asUUID).orElse(null),
                transaction.getBookYearId().asUUID());
    }

    @JsonCreator
    public RestTransaction(
            @JsonProperty("id") @Nullable final UUID id,
            @JsonProperty("bookYearId") @NotNull final UUID bookYearId) {
        this.id = id;
        this.bookYearId = bookYearId;
    }

    @JsonIgnore
    @NotNull
    public Optional<TransactionId> getId() {
        return Optional.ofNullable(id).map(TransactionId::from);
    }

    @NotNull
    public Transaction toCore() {
        return Transaction.of(Optional.ofNullable(id).map(TransactionId::from), BookYearId.from(bookYearId), LocalDate.now(), Collections.<TransactionLine>emptyList());
    }

}
