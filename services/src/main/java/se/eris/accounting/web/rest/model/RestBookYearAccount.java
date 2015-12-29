package se.eris.accounting.web.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonProperty
    private final UUID id;
    @NotNull
    @JsonProperty
    private final UUID bookYearId;
    @NotNull
    @JsonProperty
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

    @NotNull
    public BookYearAccount toCore() {
        return BookYearAccount.of(getId(), BookYearId.from(bookYearId), accountInfo.toCore());
    }

    @JsonIgnore
    @NotNull
    public Optional<BookYearAccountId> getId() {
        return Optional.ofNullable(id).map(BookYearAccountId::from);
    }

    @Override
    public String toString() {
        return "RestBookYearAccount{" +
                "id=" + id +
                ", bookYearId=" + bookYearId +
                ", accountInfo=" + accountInfo +
                '}';
    }

}
