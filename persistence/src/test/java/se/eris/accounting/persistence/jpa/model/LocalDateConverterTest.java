package se.eris.accounting.persistence.jpa.model;

import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class LocalDateConverterTest {

    @Test
    public void convertToDatabaseColumn() {
        final LocalDate localDate = LocalDate.now();

        final Date date = new LocalDateConverter().convertToDatabaseColumn(localDate);

        assertThat(date, notNullValue());
        assertThat(date.toLocalDate(), is(localDate));
    }

    @Test
    public void convertToDatabaseColumn_null() {
        final Date date = new LocalDateConverter().convertToDatabaseColumn(null);

        assertThat(date, nullValue());
    }

    @Test
    public void convertToEntityAttribute() {
        final LocalDate localDate = LocalDate.now();
        final Date sqlDate = Date.valueOf(localDate);

        final LocalDate date = new LocalDateConverter().convertToEntityAttribute(sqlDate);

        assertThat(date, notNullValue());
        assertThat(date, is(localDate));
    }

    @Test
    public void convertToEntityAttribute_null() {
        final LocalDate date = new LocalDateConverter().convertToEntityAttribute(null);

        assertThat(date, nullValue());
    }

}