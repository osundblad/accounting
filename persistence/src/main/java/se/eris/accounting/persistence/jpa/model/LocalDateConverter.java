package se.eris.accounting.persistence.jpa.model;

import org.jetbrains.annotations.Nullable;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.LocalDate;

@SuppressWarnings("unused") // used bu JPA to convert LocalDate to sql.Date
@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

    @Nullable
    @Override
    public Date convertToDatabaseColumn(@Nullable final LocalDate locDate) {
    	return ((locDate == null) ? null : Date.valueOf(locDate));
    }

    @Nullable
    @Override
    public LocalDate convertToEntityAttribute(@Nullable final Date sqlDate) {
    	return ((sqlDate == null) ? null : sqlDate.toLocalDate());
    }

}
