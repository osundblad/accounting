package se.eris.accounting.model.book;

import org.junit.Test;

import java.util.UUID;

public class BookIdTest {

    @Test
    public void from_string() {
        BookId.from(UUID.randomUUID().toString());
    }

    @Test
    public void from_uuid() {
        BookId.from(UUID.randomUUID());
    }

    @Test
    public void random() {
        BookId.random();
    }

}