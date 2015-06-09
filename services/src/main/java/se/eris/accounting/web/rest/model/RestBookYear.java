package se.eris.accounting.web.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import se.eris.accounting.model.BookYear;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RestBookYear {

    @JsonProperty
    @Nullable
    private Long id;
    @JsonProperty
    @NotNull
    private String startDate;
    @JsonProperty
    @NotNull
    private String endDate;

    @JsonCreator
    public RestBookYear(@JsonProperty("id") @Nullable final Long id, @JsonProperty("startDate") @NotNull final String startDate, @JsonProperty("endDate") @NotNull final String endDate) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public RestBookYear(@NotNull final BookYear bookYear) {
        this.id = bookYear.getIdRaw();
        this.startDate = bookYear.getStartDate().format(DateTimeFormatter.ISO_DATE);
        this.endDate = bookYear.getEndDate().format(DateTimeFormatter.ISO_DATE);
    }

    @Nullable
    public Long getId() {
        return id;
    }

    public void setId(@Nullable final Long id) {
        this.id = id;
    }

    @NotNull
    public BookYear toCore() {
        return new BookYear(id, LocalDate.parse(startDate), LocalDate.parse(endDate));
    }

    @Override
    public String toString() {
        return "RestBookYear{id=" + id + ", startDate='" + startDate + '\'' + ", endDate='" + endDate + '\'' + '}';
    }
}
