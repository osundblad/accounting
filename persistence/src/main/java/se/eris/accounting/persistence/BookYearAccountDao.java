package se.eris.accounting.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.eris.accounting.model.book.BookYearId;
import se.eris.accounting.model.book.account.BookYearAccount;
import se.eris.accounting.model.book.account.BookYearAccountId;
import se.eris.accounting.persistence.jpa.JpaBookYearAccountRepository;
import se.eris.accounting.persistence.jpa.model.JpaBookYearAccount;

import java.util.stream.Stream;

@Service
public class BookYearAccountDao {

        private final JpaBookYearAccountRepository repository;

    @Autowired
    public BookYearAccountDao(final JpaBookYearAccountRepository repository) {
        this.repository = repository;
    }

        public Stream<BookYearAccount> findBookYearAccounts(final BookYearId bookYearId) {
        return repository.readAllByBookYearId(bookYearId.asString()).map(JpaBookYearAccount::toCore);
    }

        public BookYearAccount create(final BookYearAccount account) {
        if (account.getId().isPresent()) {
            throw new AlreadyPersistedException(account + " has already been persisted (use update)." );
        }
        return repository.save(new JpaBookYearAccount(account)).toCore();
    }

    public void delete(final BookYearAccountId bookYearAccountId) {
        repository.delete(bookYearAccountId.asString());
    }

}
