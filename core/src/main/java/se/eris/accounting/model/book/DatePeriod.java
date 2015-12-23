package se.eris.accounting.model.book;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

public class DatePeriod {

    @NotNull
    public static DatePeriod between(@NotNull final LocalDate startDate, @NotNull final LocalDate endDate) {
        return new DatePeriod(startDate, endDate);
    }

    @NotNull
    private final LocalDate startDate;

    @NotNull
    private final LocalDate endDate;

    private DatePeriod(@NotNull final LocalDate startDate, @NotNull final LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        validate();
    }

    private void validate() {
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException(endDate + " is before " + startDate);
        }
    }

    @NotNull
    public LocalDate getStartDate() {
        return startDate;
    }

    @NotNull
    public LocalDate getEndDate() {
        return endDate;
    }

    @SuppressWarnings("ControlFlowStatementWithoutBraces")
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if ((o == null) || (getClass() != o.getClass())) return false;

        final DatePeriod that = (DatePeriod) o;

        return startDate.equals(that.startDate) && endDate.equals(that.endDate);
    }

    @Override
    public int hashCode() {
        return startDate.hashCode() + (31 * endDate.hashCode());
    }

    @Override
    public String toString() {
        return "DatePeriod{" + startDate + " - " + endDate + '}';
    }

}
