package se.eris.accounting.persistence.jpa;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import se.eris.accounting.persistence.jpa.model.JpaBookYearAccount;

import java.util.stream.Stream;

@Transactional
public interface JpaBookYearAccountRepository extends JpaRepository<JpaBookYearAccount, String> {

    @NotNull
    Stream<JpaBookYearAccount> readAllByBookYearId(@NotNull String bookYearId);

}
