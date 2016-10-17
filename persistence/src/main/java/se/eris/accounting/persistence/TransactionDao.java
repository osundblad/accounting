package se.eris.accounting.persistence;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.eris.accounting.model.book.BookYearId;
import se.eris.accounting.model.book.transaction.Transaction;
import se.eris.accounting.model.book.transaction.TransactionId;
import se.eris.accounting.persistence.jpa.JpaTransactionRepository;
import se.eris.accounting.persistence.jpa.model.JpaTransaction;

import java.util.stream.Stream;

@Service
public class TransactionDao {

        private final JpaTransactionRepository repository;

    @Autowired
    public TransactionDao(@NotNull final JpaTransactionRepository repository) {
        this.repository = repository;
    }

        public Stream<Transaction> findBookYearTransactions(@NotNull final BookYearId bookYearId) {
        return repository.readAllByBookYearId(bookYearId.asString()).map(JpaTransaction::toCore);
    }

        public Transaction create(@NotNull final Transaction transaction) {
        if (transaction.getId().isPresent()) {
            throw new AlreadyPersistedException(transaction + " has already been persisted (use update)." );
        }
        return repository.save(new JpaTransaction(transaction)).toCore();
    }

        public Transaction get(@NotNull final TransactionId transactionId) {
        return repository.findOne(transactionId.asString()).toCore();
    }

    public void delete(@NotNull final TransactionId transactionId) {
        repository.delete(transactionId.asString());
    }
}
