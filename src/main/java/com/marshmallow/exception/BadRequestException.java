package com.marshmallow.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends HttpException {

    public BadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

    public BadRequestException(String message, String logMessage) {
        super(HttpStatus.BAD_REQUEST, message, logMessage);
    }

}
