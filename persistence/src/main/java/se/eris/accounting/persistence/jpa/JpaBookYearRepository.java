package se.eris.accounting.persistence.jpa;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import se.eris.accounting.persistence.jpa.model.JpaBookYear;

import java.util.List;

public interface JpaBookYearRepository extends JpaRepository<JpaBookYear, String> {

    @NotNull
    List<JpaBookYear> findByBookId(@NotNull final String bookId);

}
