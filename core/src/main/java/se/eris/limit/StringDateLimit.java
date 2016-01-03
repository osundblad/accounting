package se.eris.limit;

import org.jetbrains.annotations.NotNull;
import se.eris.type.OpenDatePeriod;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class StringDateLimit implements StringLimit {

    @NotNull
    public StringDateLimit iso() {
        return of(DateTimeFormatter.ISO_DATE);
    }

    @NotNull
    public StringDateLimit iso(@NotNull final OpenDatePeriod datePeriod) {
        return of(DateTimeFormatter.ISO_DATE, datePeriod);
    }

    @NotNull
    public static StringDateLimit of(@NotNull final DateTimeFormatter dateFormat) {
        return new StringDateLimit(dateFormat, OpenDatePeriod.ALWAYS);
    }

    @NotNull
    public static StringDateLimit of(@NotNull final DateTimeFormatter dateFormat, @NotNull final OpenDatePeriod datePeriod) {
        return new StringDateLimit(dateFormat, datePeriod);
    }

    @NotNull
    private final DateTimeFormatter dateFormat;

    @NotNull
    private final OpenDatePeriod datePeriod;

    private StringDateLimit(@NotNull final DateTimeFormatter dateFormat, @NotNull final OpenDatePeriod datePeriod) {
        this.dateFormat = dateFormat;
        this.datePeriod = datePeriod;
    }

    @NotNull
    @Override
    public ValidationMessages validate(@NotNull final String s) {
        final LocalDate localDate;
        try {
            localDate = LocalDate.parse(s, dateFormat);
        } catch (final DateTimeParseException e) {
            return ValidationMessages.of("'" + s + "' is not a valid date " + dateFormat.toFormat());
        }
        if (!datePeriod.isInPeriod(localDate)) {
            return ValidationMessages.of("'" + s + "' is not in date period " + datePeriod.toString());
        }
        return ValidationMessages.empty();
    }

}
