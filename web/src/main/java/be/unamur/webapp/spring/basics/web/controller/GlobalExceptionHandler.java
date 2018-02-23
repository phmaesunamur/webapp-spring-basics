package be.unamur.webapp.spring.basics.web.controller;

import be.unamur.webapp.spring.basics.business.exception.BusinessException;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.slf4j.LoggerFactory.getLogger;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({BusinessException.class})
    public ModelAndView handleBusinessException(BusinessException ex, WebRequest request) {
        final ModelAndView mav = new ModelAndView("error");
        mav.addObject("status", HttpStatus.BAD_REQUEST);
        mav.addObject("error", ex.getClass().getSimpleName());
        mav.addObject("message", ex.getMessage());
        return mav;
    }

}
