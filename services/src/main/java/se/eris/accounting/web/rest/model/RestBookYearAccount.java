package se.eris.accounting.web.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import se.eris.accounting.model.book.BookYearId;
import se.eris.accounting.model.book.account.BookYearAccount;
import se.eris.accounting.model.book.account.BookYearAccountId;

import java.util.Optional;
import java.util.UUID;

public class RestBookYearAccount {

    @Nullable
    private final UUID id;
    @NotNull
    private final UUID bookYearId;
    @NotNull
    private final RestAccountInfo accountInfo;

    @JsonCreator
    public RestBookYearAccount(
            @JsonProperty("id") @Nullable final UUID id,
            @JsonProperty("bookYearId") @NotNull final UUID bookYearId,
            @JsonProperty("accountInfo") @NotNull final RestAccountInfo accountInfo) {
        this.id = id;
        this.bookYearId = bookYearId;
        this.accountInfo = accountInfo;
    }

    @SuppressWarnings("FeatureEnvy")
    public RestBookYearAccount(@NotNull final BookYearAccount account) {
        id = account.getId().map(BookYearAccountId::asUUID).orElse(null);
        bookYearId = account.getBookYearId().asUUID();
        accountInfo = new RestAccountInfo(account.getAccountInfo());
    }

    @Nullable
    public UUID getId() {
        return id;
    }

    @NotNull
    public UUID getBookYearId() {
        return bookYearId;
    }

    @NotNull
    public RestAccountInfo getAccountInfo() {
        return accountInfo;
    }

    @NotNull
    public BookYearAccount toCore() {
        return BookYearAccount.of(Optional.ofNullable(id).map(BookYearAccountId::from), BookYearId.from(bookYearId), accountInfo.toCore());
    }

}
