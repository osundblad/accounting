package se.eris.accounting.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.eris.accounting.model.book.Book;
import se.eris.accounting.model.book.BookId;
import se.eris.accounting.persistence.jpa.JpaBookRepository;
import se.eris.accounting.persistence.jpa.model.JpaBook;

import java.util.stream.Stream;

@Service
public class BookDao {

        private final JpaBookRepository bookRepository;

    @Autowired
    public BookDao(final JpaBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

        public Stream<Book> getAllBooks() {
        return bookRepository.findAll().stream().map(JpaBook::toCore);
    }

        public Book create(final Book book) {
        if (book.getId().isPresent()) {
            throw new AlreadyPersistedException(book + " has already been persisted (use update)." );
        }
        return bookRepository.save(new JpaBook(book)).toCore();
    }

        public Book update(final Book book) {
        if (!book.getId().isPresent()) {
            throw new NotPersistedException(book + " has not been persisted (use create)." );
        }
        return bookRepository.save(new JpaBook(book)).toCore();
    }

    public void delete(final BookId bookId) {
        bookRepository.delete(bookId.asString());
    }

}
