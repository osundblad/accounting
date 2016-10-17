package se.eris.accounting.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import se.eris.accounting.persistence.jpa.model.JpaBookYear;

import java.util.List;

@Transactional
public interface JpaBookYearRepository extends JpaRepository<JpaBookYear, String> {

        List<JpaBookYear> findByBookId(final String bookId);

}
