package se.eris.accounting;

import org.jetbrains.annotations.NotNull;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(@NotNull final HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                  .authorizeRequests()
                  .antMatchers("/index.html", "/partials/login.html", "/").permitAll().anyRequest()
                  .authenticated()
                .and()
                  .addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
                  .csrf().csrfTokenRepository(csrfTokenRepository());
    }

    @NotNull
    private CsrfTokenRepository csrfTokenRepository() {
      final HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
      repository.setHeaderName("X-XSRF-TOKEN");
      return repository;
    }

}
