package se.eris.accounting.web.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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
            @JsonProperty("accountClass") final AccountClass accountClass,
            @JsonProperty("code") final String code,
            @JsonProperty("name") final String name,
            @JsonProperty("description") final String description) {
        this.accountClass = accountClass;
        this.code = code;
        this.name = name;
        this.description = description;
    }

    @SuppressWarnings("FeatureEnvy")
    public RestAccountInfo(final AccountInfo accountInfo) {
        this(accountInfo.getAccountClass(),
                accountInfo.getCode().asString(),
                accountInfo.getName().asString(),
                accountInfo.getDescription().asString());
    }

        public AccountInfo toCore() {
        return AccountInfo.of(accountClass, AccountCode.from(code), AccountName.from(name), AccountDescription.from(description));
    }

}
