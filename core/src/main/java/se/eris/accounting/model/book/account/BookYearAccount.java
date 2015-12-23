package se.eris.accounting.model.book.account;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;

public class BookYearAccount {

    @NotNull
    private final Optional<UUID> id;

    @NotNull
    private final UUID bookYearId;

    @NotNull
    private final AccountInfo accountInfo;

    public BookYearAccount(@NotNull final Optional<UUID> id, @NotNull final UUID bookYearId, @NotNull final AccountInfo accountInfo) {
        this.id = id;
        this.bookYearId = bookYearId;
        this.accountInfo = accountInfo;
    }

    @NotNull
    public Optional<UUID> getId() {
        return id;
    }

    @NotNull
    public UUID getBookYearId() {
        return bookYearId;
    }

    @NotNull
    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

}
