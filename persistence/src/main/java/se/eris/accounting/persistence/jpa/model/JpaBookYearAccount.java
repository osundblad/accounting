package se.eris.accounting.persistence.jpa.model;

import se.eris.accounting.model.book.BookYearId;
import se.eris.accounting.model.book.account.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Entity
@Table(name = "bookYearAccount")
public class JpaBookYearAccount {

    public static final int CODE_LENGTH = 16;
    public static final int NAME_LENGTH = 200;
    @NotNull
    @Id
    @Column(nullable = false, length = 36)
    private String id;

    @NotNull
    @Column(name = "bookYearId", nullable = false, length = 36)
    private String bookYearId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AccountClass accountClass;

    @NotNull
    @Column(name = "code", nullable = false, length = CODE_LENGTH)
    private String code;

    @NotNull
    @Column(name = "name", nullable = false, length = NAME_LENGTH)
    private String name;

    @NotNull
    @Column(name = "description", nullable = false, length = 1000)
    private String description;


    @SuppressWarnings("UnusedDeclaration") // needed by Jpa framework
    public JpaBookYearAccount() {
    }

    @SuppressWarnings("FeatureEnvy")
    public JpaBookYearAccount(@NotNull final BookYearAccount account) {
        id = account.getId().orElse(BookYearAccountId.random()).asString();
        bookYearId = account.getBookYearId().asString();

        final AccountInfo accountInfo = account.getAccountInfo();
        accountClass = accountInfo.getAccountClass();
        code = accountInfo.getCode().asString();
        name = accountInfo.getName().asString();
        description = accountInfo.getDescription().asString();
    }

    @NotNull
    public BookYearAccount toCore() {
        return BookYearAccount.of(Optional.of(BookYearAccountId.from(id)), BookYearId.from(bookYearId), getAccountInfo());
    }

    @org.jetbrains.annotations.NotNull
    private AccountInfo getAccountInfo() {
        return AccountInfo.of(accountClass, AccountCode.from(code), AccountName.from(name), AccountDescription.from(description));
    }

    @SuppressWarnings({"ControlFlowStatementWithoutBraces", "NonFinalFieldReferenceInEquals"})
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if ((o == null) || (getClass() != o.getClass())) return false;

        final JpaBookYearAccount that = (JpaBookYearAccount) o;

        if (!id.equals(that.id)) return false;
        if (!bookYearId.equals(that.bookYearId)) return false;
        if (!accountClass.equals(that.accountClass)) return false;
        if (!code.equals(that.code)) return false;
        if (!name.equals(that.name)) return false;
        return description.equals(that.description);
    }

    @SuppressWarnings("NonFinalFieldReferencedInHashCode")
    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = (31 * result) + bookYearId.hashCode();
        result = (31 * result) + code.hashCode();
        result = (31 * result) + name.hashCode();
        result = (31 * result) + description.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "JpaBookYearAccount{" +
                "id='" + id + '\'' +
                ", bookYearId='" + bookYearId + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
