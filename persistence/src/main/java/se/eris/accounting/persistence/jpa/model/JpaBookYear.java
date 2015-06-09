package se.eris.accounting.persistence.jpa.model;

import org.jetbrains.annotations.NotNull;
import se.eris.accounting.model.BookYear;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "bookYear")
public class JpaBookYear {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private LocalDate start;

    @Column(nullable = false)
    private LocalDate end;


    @SuppressWarnings("UnusedDeclaration") // needed by Jpa framework
    public JpaBookYear() {
    }

    public JpaBookYear(@NotNull final BookYear bookYear) {
        id = bookYear.getIdRaw();
        start = bookYear.getStartDate();
        end = bookYear.getEndDate();
    }

    @NotNull
    public BookYear toCore() {
        return new BookYear(id, start, end);
    }

}
