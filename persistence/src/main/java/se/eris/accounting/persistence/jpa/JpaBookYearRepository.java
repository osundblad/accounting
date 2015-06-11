package se.eris.accounting.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import se.eris.accounting.persistence.jpa.model.JpaBookYear;

public interface JpaBookYearRepository extends JpaRepository<JpaBookYear, Long> {
}
