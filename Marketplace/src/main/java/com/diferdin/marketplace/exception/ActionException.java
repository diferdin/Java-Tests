package com.diferdin.marketplace.exception;

/**
 * Created by LONADF on 10/06/2016.
 */
public class ActionException extends RuntimeException {

    private final String message;

    public ActionException(String message) {
        this.message = message;
    }
}
