package be.unamur.webapp.spring.basics.business.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"be.unamur.webapp.spring.basics.business.service"})
@EnableTransactionManagement
public class BusinessConfiguration {

    @Bean
    public PlatformTransactionManager transactionManager(final DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
