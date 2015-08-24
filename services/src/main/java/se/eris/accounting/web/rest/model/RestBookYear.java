package se.eris.accounting.web.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import se.eris.accounting.model.BookYear;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class RestBookYear {

    @JsonProperty
    @Nullable
    private final String id;
    @JsonProperty
    private final String bookId;
    @JsonProperty
    @NotNull
    private final String startDate;
    @JsonProperty
    @NotNull
    private final String endDate;

    @JsonCreator
    public RestBookYear(@JsonProperty("id") @Nullable final String id, @JsonProperty("bookId") @NotNull final String bookId, @JsonProperty("startDate") @NotNull final String startDate, @JsonProperty("endDate") @NotNull final String endDate) {
        this.id = id;
        this.bookId = bookId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public RestBookYear(@NotNull final BookYear bookYear) {
        id = bookYear.hasId() ? bookYear.getIdRaw().toString() : null;
        bookId = bookYear.getBookId().toString();
        startDate = bookYear.getStartDate().format(DateTimeFormatter.ISO_DATE);
        endDate = bookYear.getEndDate().format(DateTimeFormatter.ISO_DATE);
    }

    @NotNull
    public BookYear toCore() {
        UUID id = this.id == null ? null : UUID.fromString(this.id);
        return new BookYear(id, UUID.fromString(bookId), LocalDate.parse(startDate), LocalDate.parse(endDate));
    }

    @Override
    public String toString() {
        return "RestBookYear{id=" + id + ", startDate='" + startDate + '\'' + ", endDate='" + endDate + '\'' + '}';
    }
}
