package com.diferdin.marketplace;

/**
 * Created by antonio on 01/06/2016.
 */

public abstract class Offer {

    protected String offerName;
    protected String offerSubject;

    public Offer(String offerSubject, String offerName) {
        this.offerName = offerName;
        this.offerSubject = offerSubject;
    }

    abstract double applyToList(ShoppingList shoppingList);
}
