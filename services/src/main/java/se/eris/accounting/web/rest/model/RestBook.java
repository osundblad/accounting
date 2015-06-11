package se.eris.accounting.web.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import se.eris.accounting.model.Book;

public class RestBook {

    @JsonProperty
    @Nullable
    private final Long id;
    @JsonProperty
    @NotNull
    private final String name;
    @JsonProperty
    @NotNull
    private final String description;

    @JsonCreator
    public RestBook(@JsonProperty("id") @Nullable final Long id, @JsonProperty("name") @NotNull final String name, @JsonProperty("description") @NotNull final String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public RestBook(@NotNull final Book book) {
        id = book.getIdRaw();
        name = book.getName();
        description = book.getDescription();
    }

    @NotNull
    public Book toCore() {
        return new Book(id, name, description);
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
