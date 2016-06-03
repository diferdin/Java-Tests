package com.diferdin.tests.masksandspencer.com.diferdin.tests.marksandspencer.domain;

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
