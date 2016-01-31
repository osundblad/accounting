package se.eris.accounting.persistence.jpa.model;

import org.jetbrains.annotations.Nullable;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.LocalDate;

/**
 * Used by JPA to convert a LocalDate to a sql:Date.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    @Nullable
    public Date convertToDatabaseColumn(@Nullable final LocalDate localDate) {
    	return ((localDate == null) ? null : Date.valueOf(localDate));
    }

    @Override
    @Nullable
    public LocalDate convertToEntityAttribute(@Nullable final Date sqlDate) {
    	return ((sqlDate == null) ? null : sqlDate.toLocalDate());
    }

}
