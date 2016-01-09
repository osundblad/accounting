package se.eris.type;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Optional;

public class OpenDatePeriod extends PairWrapper<Optional<LocalDate>> {

    @NotNull
    public static OpenDatePeriod of(@NotNull final Optional<LocalDate> startDate, @NotNull final Optional<LocalDate> endDate) {
        return new OpenDatePeriod(startDate, endDate);
    }

    @NotNull
    public static OpenDatePeriod from(@NotNull final LocalDate startDate) {
        return of(Optional.of(startDate), Optional.empty());
    }

    @NotNull
    public static OpenDatePeriod to(@NotNull final LocalDate endDate) {
        return new OpenDatePeriod(Optional.empty(), Optional.of(endDate));
    }

    @NotNull
    public static OpenDatePeriod between(@NotNull final LocalDate startDate, @NotNull final LocalDate endDate) {
        return of(Optional.of(startDate), Optional.of(endDate));
    }

    @NotNull
    public static final OpenDatePeriod ALWAYS = of(Optional.empty(), Optional.empty());

    private OpenDatePeriod(@NotNull final Optional<LocalDate> startDate, @NotNull final Optional<LocalDate> endDate) {
        super(startDate, endDate);
        validate();
    }

    private void validate() {
        if (isEndBeforeStart()) {
            throw new IllegalArgumentException(rawSecond() + " is before " + rawSecond());
        }
    }

    private boolean isEndBeforeStart() {
        return hasStart() && hasEnd() && rawSecond().get().isBefore(rawFirst().get());
    }

    public boolean hasStart() {
        return rawFirst().isPresent();
    }

    @NotNull
    public LocalDate getStartDate() {
        return rawFirst().get();
    }

    public boolean hasEnd() {
        return rawSecond().isPresent();
    }

    @NotNull
    public LocalDate getEndDate() {
        return rawSecond().get();
    }

    public boolean isInPeriod(@NotNull final ChronoLocalDate localDate) {
        if (hasStart() && localDate.isBefore(getStartDate())) {
            return false;
        }
        //noinspection RedundantIfStatement
        if (hasEnd() && localDate.isAfter(getEndDate())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final String from = rawFirst().isPresent() ? rawFirst().toString() : "";
        final String to = rawSecond().isPresent() ? rawSecond().toString() : "";
        return "DatePeriod{" + from + " - " + to + '}';
    }
}
