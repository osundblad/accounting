package se.eris.accounting;

import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.isdc.wro.http.WroFilter;

import javax.servlet.Filter;

@Configuration
class WebConfiguration {

    @NotNull
    private Filter wroFilter() {
        return new WroFilter();
    }

    @NotNull
    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(wroFilter());
        registration.addUrlPatterns("/wro/*");
        registration.setName("wroFilter");
        return registration;
    }

}
