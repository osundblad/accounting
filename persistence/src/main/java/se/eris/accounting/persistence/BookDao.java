package se.eris.accounting.persistence;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.eris.accounting.model.book.Book;
import se.eris.accounting.model.book.BookId;
import se.eris.accounting.persistence.jpa.JpaBookRepository;
import se.eris.accounting.persistence.jpa.model.JpaBook;

import java.util.stream.Stream;

@Service
public class BookDao {

    @NotNull
    private final JpaBookRepository bookRepository;

    @Autowired
    public BookDao(@NotNull final JpaBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @NotNull
    public Stream<Book> getAllBooks() {
        return bookRepository.findAll().stream().map(JpaBook::toCore);
    }

    @NotNull
    public Book create(@NotNull final Book book) {
        if (book.getId().isPresent()) {
            throw new AlreadyPersistedException(book + " has already been persisted (use update)." );
        }
        return bookRepository.save(new JpaBook(book)).toCore();
    }

    public void delete(@NotNull final BookId bookId) {
        bookRepository.delete(bookId.asString());
    }

}
