package se.eris.accounting.web.rest;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import se.eris.accounting.model.BookYear;
import se.eris.accounting.services.RestApiService;
import se.eris.accounting.web.rest.model.RestBookYear;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/year", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookYearResource {

    private static final Logger logger = LoggerFactory.getLogger(BookYearResource.class);

    @NotNull
    private final RestApiService restApiService;

    @Autowired
    public BookYearResource(@NotNull final RestApiService restApiService) {
        this.restApiService = restApiService;
    }

    @NotNull
    @RequestMapping(method = RequestMethod.GET)
    public List<RestBookYear> getAll() {
        return restApiService.getAllBookYears().map(RestBookYear::new).collect(Collectors.toList());
    }

    @NotNull
    @RequestMapping(method = RequestMethod.GET, value = "/template")
    public RestBookYear getTemplate() {
        final int year = LocalDate.now().getYear();
        return new RestBookYear(new BookYear(null, LocalDate.of(year, Month.JANUARY, 1), LocalDate.of(year, Month.DECEMBER, 31)));
    }

    @NotNull
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    RestBookYear create(@RequestBody @NotNull final RestBookYear restBookYear) {
        final BookYear saved = restApiService.create(restBookYear.toCore());
        logger.debug("created book year: " + saved);
        return new RestBookYear(saved);
    }

}
