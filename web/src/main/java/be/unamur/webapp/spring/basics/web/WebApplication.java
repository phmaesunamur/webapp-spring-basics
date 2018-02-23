package be.unamur.webapp.spring.basics.web;

import be.unamur.webapp.spring.basics.business.config.BusinessConfiguration;
import be.unamur.webapp.spring.basics.dataaccess.config.DataAccessConfiguration;
import be.unamur.webapp.spring.basics.web.config.WebConfiguration;
import be.unamur.webapp.spring.basics.web.config.WebSecurityConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({WebConfiguration.class, BusinessConfiguration.class, DataAccessConfiguration.class, WebSecurityConfiguration.class})
public class WebApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder builder) {
        return builder.sources(WebApplication.class);
    }

}
