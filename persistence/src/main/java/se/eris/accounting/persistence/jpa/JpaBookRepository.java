package se.eris.accounting.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import se.eris.accounting.persistence.jpa.model.JpaBook;

@Transactional
public interface JpaBookRepository extends JpaRepository<JpaBook, String> {
}
