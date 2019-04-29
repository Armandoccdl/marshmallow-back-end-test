package com.marshmallow.exception;

import org.springframework.http.HttpStatus;

/**
 * Generic class for handling HttpExceptions. Any extending class needs to provide http status and
 * the message that is returned to view. Also more detailed log message can be provided about the com.example.marshmallow.exception.
 */
public abstract class HttpException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String logMessage;

    HttpException(HttpStatus httpStatus, String message) {
        this(httpStatus, message, message);
    }

    HttpException(HttpStatus httpStatus, String message, String logMessage) {
        super(message);
        this.httpStatus = httpStatus;
        this.logMessage = logMessage;
    }

    /**
     * Returns Http Status for this com.example.marshmallow.exception
     *
     * @return HttpStatus object
     */
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    /**
     * Returns detailed log message.
     *
     * @return String message
     */
    public String getLogMessage() {
        return logMessage;
    }

}