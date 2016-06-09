package com.diferdin.marketplace.exception;

import com.sun.org.apache.xpath.internal.operations.Or;

/**
 * Created by LONADF on 09/06/2016.
 */
public class OrderException extends RuntimeException {
    private final String message;

    public OrderException(String message) {
        this.message = message;
    }
}
