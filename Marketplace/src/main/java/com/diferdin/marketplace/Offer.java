package com.diferdin.marketplace;

/**
 * Created by LONADF on 09/06/2016.
 */
public class Offer extends Action {

    public Offer(String itemId, int quantity, int pricePerUnit, String user) {
        super(ActionType.OFFER, itemId, quantity, pricePerUnit, user);
    }
}
