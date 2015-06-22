package se.eris.accounting;

import org.jetbrains.annotations.NotNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
//@Import(value = {ConfigInMemoryH2.class})
public class Application {

    public static void main(@NotNull final String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

}