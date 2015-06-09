package se.eris.accounting.services;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.eris.accounting.model.BookYear;
import se.eris.accounting.persistence.BookYearDataService;

import java.util.stream.Stream;

@Service
public class RestApiService {

    private final BookYearDataService bookYearDataService;

    @Autowired
    public RestApiService(@NotNull final BookYearDataService bookYearDataService) {
        this.bookYearDataService = bookYearDataService;
    }


    @NotNull
    public BookYear create(@NotNull final BookYear bookYear) {
        return bookYearDataService.create(bookYear);
    }

    @NotNull
    public Stream<BookYear> getAllBookYears() {
        return bookYearDataService.getAllBookYears();
    }
}
