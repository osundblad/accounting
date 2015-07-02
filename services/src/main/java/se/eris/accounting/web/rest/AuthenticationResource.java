package se.eris.accounting.web.rest;

import org.jetbrains.annotations.Nullable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationResource {

    @Nullable
    @RequestMapping("")
    public Principal user(@Nullable final Principal user) {
        return user;
    }

}
