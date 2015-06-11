package se.eris.accounting.persistence.jpa.model;

import org.jetbrains.annotations.NotNull;
import se.eris.accounting.model.Book;

import javax.persistence.*;

@Entity
@Table(name = "book")
public class JpaBook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 2000)
    private String description;


    @SuppressWarnings("UnusedDeclaration") // needed by Jpa framework
    public JpaBook() {
    }

    public JpaBook(@NotNull final Book book) {
        id = book.getIdRaw();
        name = book.getName();
        description = book.getDescription();
    }

    @NotNull
    public Book toCore() {
        return new Book(id, name, description);
    }

}
