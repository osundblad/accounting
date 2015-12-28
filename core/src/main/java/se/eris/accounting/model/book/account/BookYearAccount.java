package se.eris.accounting.model.book.account;

import org.jetbrains.annotations.NotNull;
import se.eris.accounting.model.book.BookYearId;

import java.util.Optional;

public class BookYearAccount {

    @NotNull
    public static BookYearAccount of(@NotNull final Optional<BookYearAccountId> id, @NotNull final BookYearId bookYearId, @NotNull final AccountInfo accountInfo) {
        return new BookYearAccount(id, bookYearId, accountInfo);
    }

    @NotNull
    private final Optional<BookYearAccountId> id;

    @NotNull
    private final BookYearId bookYearId;

    @NotNull
    private final AccountInfo accountInfo;

    private BookYearAccount(@NotNull final Optional<BookYearAccountId> id, @NotNull final BookYearId bookYearId, @NotNull final AccountInfo accountInfo) {
        this.id = id;
        this.bookYearId = bookYearId;
        this.accountInfo = accountInfo;
    }

    @NotNull
    public Optional<BookYearAccountId> getId() {
        return id;
    }

    @NotNull
    public BookYearId getBookYearId() {
        return bookYearId;
    }

    @NotNull
    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

}
