package com.diferdin.marketplace;

/**
 * Created by LONADF on 02/06/2016.
 */
public enum ChargeType {
    DELIVERY_CHARGE("DELIVERY_CHARGE");

    private final String name;

    ChargeType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
