package se.eris.accounting.persistence;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(value = "se.eris.accounting.persistence.jpa", entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager")
public class ConfigProdDb {

    @Bean
    @ConfigurationProperties(prefix="mariadb.datasource")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

}
