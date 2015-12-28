package se.eris.accounting.persistence;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.eris.accounting.model.book.BookYearId;
import se.eris.accounting.model.book.account.BookYearAccount;
import se.eris.accounting.persistence.jpa.JpaBookYearAccountRepository;
import se.eris.accounting.persistence.jpa.model.JpaBookYearAccount;

import java.util.stream.Stream;

@Service
public class BookYearAccountDao {

    @NotNull
    private final JpaBookYearAccountRepository repository;

    @Autowired
    public BookYearAccountDao(@NotNull final JpaBookYearAccountRepository repository) {
        this.repository = repository;
    }

    @NotNull
    public Stream<BookYearAccount> findBookYearAccounts(@NotNull final BookYearId bookYearId) {
        return repository.readAllByBookYearId(bookYearId.asString()).map(JpaBookYearAccount::toCore);
    }

    @NotNull
    public BookYearAccount create(@NotNull final BookYearAccount account) {
        if (account.getId().isPresent()) {
            throw new AlreadyPersistedException(account + " has already been persisted (use update)." );
        }
        return repository.save(new JpaBookYearAccount(account)).toCore();
    }

}
