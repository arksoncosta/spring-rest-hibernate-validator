package br.com.arkson.springhibernatevalidation.exceptions;

/**
 * @author arkson
 * @date 30/04/2021
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
