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
    private final Long id;
    @JsonProperty
    private final long bookId;
    @JsonProperty
    @NotNull
    private final String startDate;
    @JsonProperty
    @NotNull
    private final String endDate;

    @JsonCreator
    public RestBookYear(@JsonProperty("id") @Nullable final Long id, @JsonProperty("bookId") final long bookId, @JsonProperty("startDate") @NotNull final String startDate, @JsonProperty("endDate") @NotNull final String endDate) {
        this.id = id;
        this.bookId = bookId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public RestBookYear(@NotNull final BookYear bookYear) {
        id = bookYear.getIdRaw();
        bookId = bookYear.getBookId();
        startDate = bookYear.getStartDate().format(DateTimeFormatter.ISO_DATE);
        endDate = bookYear.getEndDate().format(DateTimeFormatter.ISO_DATE);
    }

    @NotNull
    public BookYear toCore() {
        return new BookYear(id, bookId, LocalDate.parse(startDate), LocalDate.parse(endDate));
    }

    @Override
    public String toString() {
        return "RestBookYear{id=" + id + ", startDate='" + startDate + '\'' + ", endDate='" + endDate + '\'' + '}';
    }
}
