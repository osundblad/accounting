package se.eris.accounting.web.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import se.eris.accounting.model.book.account.AccountInfo;

public class RestAccountInfo {

    @NotNull
    private final String code;
    @NotNull
    private final String name;
    @NotNull
    private final String description;

    @JsonCreator
    public RestAccountInfo(
            @JsonProperty("code") @NotNull final String code,
            @JsonProperty("name") @NotNull final String name,
            @JsonProperty("descriptionInfo") @NotNull final String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    @SuppressWarnings("FeatureEnvy")
    public RestAccountInfo(@NotNull final AccountInfo accountInfo) {
        code = accountInfo.getCode();
        name = accountInfo.getName();
        description = accountInfo.getDescription();
    }

    @NotNull
    public String getCode() {
        return code;
    }

    @NotNull
    public String getName() {
        return name;
    }

    @NotNull
    public String getDescription() {
        return description;
    }

}
