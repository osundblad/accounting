package se.eris.accounting.model.book.account;

public class AccountInfo {

    public static AccountInfo of(final AccountClass accountClass, final AccountCode code, final AccountName name, final AccountDescription description) {
        return new AccountInfo(accountClass, code, name, description);
    }

    private final AccountClass accountClass;

    private final AccountCode code;

    private final AccountName name;

    private final AccountDescription description;

    private AccountInfo(final AccountClass accountClass, final AccountCode code, final AccountName name, final AccountDescription description) {
        this.accountClass = accountClass;
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public AccountClass getAccountClass() {
        return accountClass;
    }

    public AccountCode getCode() {
        return code;
    }

    public AccountName getName() {
        return name;
    }

    public AccountDescription getDescription() {
        return description;
    }

}
