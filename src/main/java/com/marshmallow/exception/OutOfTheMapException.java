package com.marshmallow.exception;

import org.springframework.http.HttpStatus;

public class OutOfTheMapException extends HttpException {

    public OutOfTheMapException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

    public OutOfTheMapException(String message, String logMessage) {
        super(HttpStatus.BAD_REQUEST, message, logMessage);
    }

}
