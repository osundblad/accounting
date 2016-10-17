package se.eris.accounting.persistence.jpa;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import se.eris.accounting.persistence.jpa.model.JpaTransaction;

import java.util.stream.Stream;

@Transactional
public interface JpaTransactionRepository extends JpaRepository<JpaTransaction, String> {

        Stream<JpaTransaction> readAllByBookYearId(@NotNull String bookYearId);

}
