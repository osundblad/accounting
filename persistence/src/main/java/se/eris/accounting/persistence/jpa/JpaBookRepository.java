package se.eris.accounting.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import se.eris.accounting.persistence.jpa.model.JpaBook;

public interface JpaBookRepository extends JpaRepository<JpaBook, String> {
}
