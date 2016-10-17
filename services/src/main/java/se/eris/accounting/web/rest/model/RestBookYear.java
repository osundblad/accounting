package se.eris.accounting.web.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;
import se.eris.accounting.model.book.BookId;
import se.eris.accounting.model.book.BookYear;
import se.eris.accounting.model.book.BookYearId;
import se.eris.jtype.type.OpenDatePeriod;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

public class RestBookYear {

    @JsonProperty
    @Nullable
    private final UUID id;
    @JsonProperty
        private final UUID bookId;
    @JsonProperty
        private final String startDate;
    @JsonProperty
        private final String endDate;

    @JsonCreator
    public RestBookYear(
            @JsonProperty("id") @Nullable final UUID id,
            @JsonProperty("bookId") final UUID bookId,
            @JsonProperty("startDate") final String startDate,
            @JsonProperty("endDate") final String endDate) {
        this.id = id;
        this.bookId = bookId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @SuppressWarnings("FeatureEnvy")
    public RestBookYear(final BookYear bookYear) {
        id = bookYear.getId().map(BookYearId::asUUID).orElse(null);
        bookId = bookYear.getBookId().asUUID();
        startDate = bookYear.getStartDate().format(DateTimeFormatter.ISO_DATE);
        endDate = bookYear.getEndDate().format(DateTimeFormatter.ISO_DATE);
    }

    @JsonIgnore
        public Optional<UUID> getId() {
        return Optional.ofNullable(id);
    }

        public BookYear toCore() {
        return new BookYear(Optional.ofNullable(id).map(BookYearId::from), BookId.from(bookId), OpenDatePeriod.between(LocalDate.parse(startDate), LocalDate.parse(endDate)));
    }

    @Override
    public String toString() {
        return "RestBookYear{id=" + id + ", startDate='" + startDate + '\'' + ", endDate='" + endDate + '\'' + '}';
    }
}
