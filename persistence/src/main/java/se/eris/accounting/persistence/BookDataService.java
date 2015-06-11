package se.eris.accounting.persistence;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.eris.accounting.model.Book;
import se.eris.accounting.persistence.jpa.JpaBookRepository;
import se.eris.accounting.persistence.jpa.model.JpaBook;

import java.util.stream.Stream;

@Service
public class BookDataService {

    private final JpaBookRepository bookRepository;

    @Autowired
    public BookDataService(@NotNull final JpaBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @NotNull
    public Stream<Book> getAllBooks() {
        return bookRepository.findAll().stream().map(JpaBook::toCore);
    }

    @NotNull
    public Book create(@NotNull final Book book) {
        if (book.hasId()) {
            throw new AlreadyPersistedException(book + " has already been persisted (use update)." );
        }
        return bookRepository.save(new JpaBook(book)).toCore();
    }

}
