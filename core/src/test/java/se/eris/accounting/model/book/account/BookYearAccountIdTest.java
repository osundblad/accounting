package se.eris.accounting.model.book.account;

import org.junit.Test;

import java.util.UUID;

public class BookYearAccountIdTest {

    @Test
    public void from_string() {
        BookYearAccountId.from(UUID.randomUUID().toString());
    }

    @Test
    public void from_uuid() {
        BookYearAccountId.from(UUID.randomUUID());
    }

    @Test
    public void random() {
        BookYearAccountId.random();
    }

}