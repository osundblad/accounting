package se.eris.accounting.web.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import se.eris.accounting.model.book.BookYear;
import se.eris.accounting.model.book.DatePeriod;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

public class RestBookYear {

    @JsonProperty
    @Nullable
    private final UUID id;
    @JsonProperty
    @NotNull
    private final UUID bookId;
    @JsonProperty
    @NotNull
    private final String startDate;
    @JsonProperty
    @NotNull
    private final String endDate;

    @JsonCreator
    public RestBookYear(
            @JsonProperty("id") @Nullable final UUID id,
            @JsonProperty("bookId") @NotNull final UUID bookId,
            @JsonProperty("startDate") @NotNull final String startDate,
            @JsonProperty("endDate") @NotNull final String endDate) {
        this.id = id;
        this.bookId = bookId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @SuppressWarnings("FeatureEnvy")
    public RestBookYear(@NotNull final BookYear bookYear) {
        id = bookYear.getId().orElse(null);
        bookId = bookYear.getBookId();
        startDate = bookYear.getStartDate().format(DateTimeFormatter.ISO_DATE);
        endDate = bookYear.getEndDate().format(DateTimeFormatter.ISO_DATE);
    }

    @NotNull
    public BookYear toCore() {
        return new BookYear(Optional.ofNullable(id), bookId, DatePeriod.between(LocalDate.parse(startDate), LocalDate.parse(endDate)));
    }

    @Override
    public String toString() {
        return "RestBookYear{id=" + id + ", startDate='" + startDate + '\'' + ", endDate='" + endDate + '\'' + '}';
    }
}
