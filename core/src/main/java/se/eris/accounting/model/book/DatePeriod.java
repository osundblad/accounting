package se.eris.accounting.model.book;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

public class DatePeriod {

    @NotNull
    private final LocalDate startDate;
    @NotNull
    private final LocalDate endDate;

    public DatePeriod(@NotNull final LocalDate startDate, @NotNull final LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        validate();
    }

    private void validate() {
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException();
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
        int result = startDate.hashCode();
        result = (31 * result) + endDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "DatePeriod{" + startDate + " - " + endDate + '}';
    }

}