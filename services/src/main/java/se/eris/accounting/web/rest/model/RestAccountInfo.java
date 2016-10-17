package se.eris.accounting.web.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import se.eris.accounting.model.book.account.*;

public class RestAccountInfo {

        @JsonProperty
    private final AccountClass accountClass;
        @JsonProperty
    private final String code;
        @JsonProperty
    private final String name;
        @JsonProperty
    private final String description;

    @JsonCreator
    public RestAccountInfo(
            @JsonProperty("accountClass") @NotNull final AccountClass accountClass,
            @JsonProperty("code") @NotNull final String code,
            @JsonProperty("name") @NotNull final String name,
            @JsonProperty("description") @NotNull final String description) {
        this.accountClass = accountClass;
        this.code = code;
        this.name = name;
        this.description = description;
    }

    @SuppressWarnings("FeatureEnvy")
    public RestAccountInfo(@NotNull final AccountInfo accountInfo) {
        this(accountInfo.getAccountClass(),
                accountInfo.getCode().asString(),
                accountInfo.getName().asString(),
                accountInfo.getDescription().asString());
    }

        public AccountInfo toCore() {
        return AccountInfo.of(accountClass, AccountCode.from(code), AccountName.from(name), AccountDescription.from(description));
    }

}
