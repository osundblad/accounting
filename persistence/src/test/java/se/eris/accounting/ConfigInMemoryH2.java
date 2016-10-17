package se.eris.accounting;

import liquibase.integration.spring.SpringLiquibase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(value = "se.eris.accounting.persistence.jpa", entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager")
@EnableTransactionManagement
public class ConfigInMemoryH2 {

    private static final Logger logger = LoggerFactory.getLogger(ConfigInMemoryH2.class);

    @Resource
    private Environment env;

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        final String mode = getDbMode();
        logger.info("db.mode: " + mode);
        dataSource.setUrl("jdbc:h2:mem:accounting;DB_CLOSE_DELAY=-1;MODE=" + mode);
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

        private String getDbMode() {
        final String property = env.getProperty("db.mode");
        return (property != null) ? property : "MySQL";
    }

    @Bean(name = "transactionManager")
    public JpaTransactionManager transactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }

    @Bean(name = "entityManagerFactory")
    @DependsOn("liquibase")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setPersistenceUnitName("accountingPU");
        entityManager.setDataSource(dataSource());
        entityManager.setJpaVendorAdapter(jpaVendorAdapter());
        entityManager.setPackagesToScan("se.eris.accounting.persistence.jpa.model");
        entityManager.setJpaProperties(jpaProperties());
        return entityManager;
    }

    @Bean(name = "vendorAdapter")
    public JpaVendorAdapter jpaVendorAdapter() {
        final HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabase(Database.H2);
        jpaVendorAdapter.setShowSql(false);
        jpaVendorAdapter.setGenerateDdl(false);
        return jpaVendorAdapter;
    }

        @Bean(name = "liquibase")
    @DependsOn("dataSource")
    public SpringLiquibase liquibase() {
        final SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource());
        liquibase.setChangeLog("classpath:db/db.changelog-master.yaml");
        liquibase.setContexts("production");
        return liquibase;
    }

        private Properties jpaProperties() {
        final Properties properties = new Properties();
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
        return properties;
    }

}
