package se.eris.limit;

import org.jetbrains.annotations.NotNull;
import se.eris.type.OpenDatePeriod;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateLimit implements StringLimit {

    @NotNull
    public DateLimit iso() {
        return of(DateTimeFormatter.ISO_DATE);
    }

    @NotNull
    public static DateLimit of(@NotNull final DateTimeFormatter dateFormat) {
        return new DateLimit(dateFormat, OpenDatePeriod.ALWAYS);
    }

    @NotNull
    private final DateTimeFormatter dateFormat;

    @NotNull
    private final OpenDatePeriod datePeriod;

    private DateLimit(@NotNull final DateTimeFormatter dateFormat, @NotNull final OpenDatePeriod datePeriod) {
        this.dateFormat = dateFormat;
        this.datePeriod = datePeriod;
    }

    @Override
    public void validate(@NotNull final String s) {
        final LocalDate localDate;
        try {
            localDate = LocalDate.parse(s, dateFormat);
        } catch (final DateTimeParseException e) {
            throw new IllegalArgumentException("'" + s + "' is not a valid date " + dateFormat.toFormat());
        }
        if (!datePeriod.isInPeriod(localDate)) {
            throw new IllegalArgumentException("'" + s + "' is not in date period " + datePeriod.toString());
        }
    }

}
