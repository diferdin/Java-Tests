package com.diferdin.marketplace;

/**
 * Created by LONADF on 08/06/2016.
 */

public class Bid extends Action{

    public Bid(String itemId, int quantity, int pricePerUnit, String user) {
        super(ActionType.BID, itemId, quantity, pricePerUnit, user);
    }
}
