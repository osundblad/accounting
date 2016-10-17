package se.eris.accounting.model.book.account;

import se.eris.accounting.model.book.BookYearId;

import java.util.Optional;

public class BookYearAccount {

        public static BookYearAccount of(final Optional<BookYearAccountId> id, final BookYearId bookYearId, final AccountInfo accountInfo) {
        return new BookYearAccount(id, bookYearId, accountInfo);
    }

        private final Optional<BookYearAccountId> id;

        private final BookYearId bookYearId;

        private final AccountInfo accountInfo;

    private BookYearAccount(final Optional<BookYearAccountId> id, final BookYearId bookYearId, final AccountInfo accountInfo) {
        this.id = id;
        this.bookYearId = bookYearId;
        this.accountInfo = accountInfo;
    }

        public Optional<BookYearAccountId> getId() {
        return id;
    }

        public BookYearId getBookYearId() {
        return bookYearId;
    }

        public AccountInfo getAccountInfo() {
        return accountInfo;
    }

}
