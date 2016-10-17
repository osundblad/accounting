package se.eris.accounting.persistence;

import org.springframework.boot.context.properties.ConfigurationProperties;

@SuppressWarnings("unused")
@ConfigurationProperties(prefix="mariadb")
public class MariaDBProperties {

        private Datasource datasource;

    public Datasource getDatasource() {
        return datasource;
    }

    public void setDatasource(final Datasource datasource) {
        this.datasource = datasource;
    }

    private static class Datasource {

        private String url = "jdbc:mariadb://localhost/accounting";
        private Class driverClassName = org.mariadb.jdbc.Driver.class;
        private String userName = "book";
        private String password;

        public String getUrl() {
            return url;
        }

        public void setUrl(final String url) {
            this.url = url;
        }

        public Class getDriverClassName() {
            return driverClassName;
        }

        public void setDriverClassName(final Class driverClassName) {
            this.driverClassName = driverClassName;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(final String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(final String password) {
            this.password = password;
        }

    }

}
