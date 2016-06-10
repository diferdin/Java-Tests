package com.diferdin.marketplace;

/**
 * Created by LONADF on 10/06/2016.
 */
public enum ActionType {

    BID("BID"),
    OFFER("OFFER"),
    ORDER("ORDER");

    private final String type;

    ActionType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
