package se.eris.accounting.model.book;

import org.jetbrains.annotations.NotNull;
import se.eris.type.SimplePair;

import java.time.LocalDate;

public class DatePeriod extends SimplePair<LocalDate> {

    @NotNull
    public static DatePeriod between(@NotNull final LocalDate startDate, @NotNull final LocalDate endDate) {
        return new DatePeriod(startDate, endDate);
    }

    private DatePeriod(@NotNull final LocalDate startDate, @NotNull final LocalDate endDate) {
        super(startDate, endDate);
        validate();
    }

    private void validate() {
        if (rawSecond().isBefore(rawFirst())) {
            throw new IllegalArgumentException(rawSecond() + " is before " + rawSecond());
        }
    }

    @NotNull
    public LocalDate getStartDate() {
        return rawFirst();
    }

    @NotNull
    public LocalDate getEndDate() {
        return rawSecond();
    }

    @Override
    public String toString() {
        return "DatePeriod{" + rawFirst() + " - " + rawSecond() + '}';
    }

}
