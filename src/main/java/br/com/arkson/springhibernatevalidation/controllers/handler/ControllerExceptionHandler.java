package br.com.arkson.springhibernatevalidation.controllers.handler;

import br.com.arkson.springhibernatevalidation.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

/**
 * @author arkson
 * @date 30/04/2021
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({NotFoundException.class, ConstraintViolationException.class, NumberFormatException.class})
    public ResponseEntity<Object> handleException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
