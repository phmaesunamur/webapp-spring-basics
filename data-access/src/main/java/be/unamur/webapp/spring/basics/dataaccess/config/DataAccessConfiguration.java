package be.unamur.webapp.spring.basics.dataaccess.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"be.unamur.webapp.spring.basics.dataaccess.repository"})
public class DataAccessConfiguration {

    //DataSourceAutoConfiguration
    //DataSourceConfiguration
    //DataSourceBuilder & -> DatabaseDriver to determine the driver
    //DataSourcePoolMetadataProvidersConfiguration -> HikariPoolDataSourceMetadataProviderConfiguration

}
