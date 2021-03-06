package se.eris.accounting.web.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;
import se.eris.accounting.model.book.Book;
import se.eris.accounting.model.book.BookDescription;
import se.eris.accounting.model.book.BookId;
import se.eris.accounting.model.book.BookName;

import java.util.Optional;
import java.util.UUID;

public class RestBook {

    @JsonProperty
    @Nullable
    private final UUID id;
    @JsonProperty
        private final String name;
    @JsonProperty
        private final String description;

    @JsonCreator
    public RestBook(@JsonProperty("id") @Nullable final UUID id, @JsonProperty("name") final String name, @JsonProperty("description") final String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @SuppressWarnings("FeatureEnvy")
    public RestBook(final Book book) {
        id = book.getId().map(BookId::asUUID).orElse(null);
        name = book.getName().asString();
        description = book.getDescription().asString();
    }

    @JsonIgnore
        public Optional<BookId> getBookId() {
        return Optional.ofNullable(id).map(BookId::from);
    }

        public Book toCore() {
        return new Book(getBookId(), BookName.of(name), BookDescription.of(description));
    }

        @Override
    public String toString() {
        return "RestBook{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
