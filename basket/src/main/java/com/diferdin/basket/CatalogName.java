package com.diferdin.basket;

/**
 * Created by LONADF on 02/06/2016.
 */
public enum CatalogName {
    CLOTHING("CLOTHING");

    private final String name;

    CatalogName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
