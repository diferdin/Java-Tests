package com.diferdin.tests.masksandspencer;

import java.util.List;

/**
 * Created by antonio on 01/06/2016.
 */

public abstract class Offer {

    protected String offerName;
    protected String offerSubject;

    public Offer(String offerSubject, String offerName) {
        this.offerName = offerName;
        this.offerSubject = offerSubject;
        Offers.add(this);
    }

    abstract double applyToList(List<Product> shoppingList);
}
