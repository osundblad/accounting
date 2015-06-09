package se.eris.accounting.web.rest;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import se.eris.accounting.web.rest.model.RestBookYear;

import static org.junit.Assert.assertTrue;

public class BookYearResourceTest {

    @Test
    public void testThatCanConvertUpdateModel() {
        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

        assertTrue(converter.canRead(RestBookYear.class, MediaType.APPLICATION_JSON));
    }

}