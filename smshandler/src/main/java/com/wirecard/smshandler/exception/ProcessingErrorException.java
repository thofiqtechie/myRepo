package com.wirecard.smshandler.exception;

import org.apache.log4j.Logger;

/**
 * Created by thofi_000 on 11/18/2017.
 * Processing Error Exception - Runtime if any error during processing
 */
public class ProcessingErrorException extends RuntimeException {

    private static final long serialVersionUID = 100L;

    private static Logger log = Logger.getLogger(ProcessingErrorException.class);

    private String message;

    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public ProcessingErrorException(String message) {
        this.message = message;
    }

    /**
     * Returns the detail message string of this throwable.
     *
     * @return the detail message string of this {@code Throwable} instance
     * (which may be {@code null}).
     */
    @Override
    public String getMessage() {
        return this.message;
    }
}
