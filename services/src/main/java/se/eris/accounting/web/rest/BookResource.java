package se.eris.accounting.web.rest;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import se.eris.accounting.model.Book;
import se.eris.accounting.services.RestApiService;
import se.eris.accounting.web.rest.model.RestBook;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/book", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookResource {

    private static final Logger logger = LoggerFactory.getLogger(BookResource.class);

    @NotNull
    private final RestApiService restApiService;

    @Autowired
    public BookResource(@NotNull final RestApiService restApiService) {
        this.restApiService = restApiService;
    }

    @NotNull
    @RequestMapping(method = RequestMethod.GET)
    public List<RestBook> getAll() {
        return restApiService.getAllBooks().map(RestBook::new).collect(Collectors.toList());
    }

    @NotNull
    @RequestMapping(method = RequestMethod.GET, value = "/{bookId}")
    public RestBook get(@PathVariable("bookId") final UUID bookId) {
        final Book book = restApiService.getAllBooks().filter(b -> b.getId().equals(bookId)).findFirst().orElseThrow(() -> new NotFoundException("no " + Book.class.getSimpleName() + " with id " + bookId + " found."));
        return new RestBook(book);
    }

    @NotNull
    @RequestMapping(method = RequestMethod.GET, value = "/template")
    public RestBook getTemplate() {
        return new RestBook(new Book(null, "", ""));
    }

    @NotNull
    @RequestMapping(method = RequestMethod.GET, value = "/create")
    public RestBook createRandom(@RequestParam("name") final String name, @RequestParam("description") final String description) {
        return new RestBook(restApiService.create(new Book(null, name, description)));
    }

    @NotNull
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public
    RestBook create(@RequestBody @NotNull final RestBook restBook) {
        final Book saved = restApiService.create(restBook.toCore());
        logger.debug("created book: " + saved);
        return new RestBook(saved);
    }

}
