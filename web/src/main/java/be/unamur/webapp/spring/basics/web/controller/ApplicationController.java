package be.unamur.webapp.spring.basics.web.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApplicationController {

    @RequestMapping(path = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String serveHomePage() {
        return "index";
    }

    @RequestMapping(path = "/signup", produces = MediaType.TEXT_HTML_VALUE)
    public String serveSignUpPage() {
        return "signup";
    }

    @RequestMapping(path = "/about", produces = MediaType.TEXT_HTML_VALUE)
    public String serveAboutPage() {
        return "about";
    }

}
