package com.diferdin.marketplace;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by LONADF on 08/06/2016.
 */

public class Bid extends Action{

    private static final ActionType BID_TYPE = ActionType.BID;

    public Bid(String itemId, int quantity, int pricePerUnit, String user) {
        super(BID_TYPE, itemId, quantity, pricePerUnit, user);
    }

    @Override
    public int hashCode() {
        return BID_TYPE.hashCode() +
                itemId.hashCode() +
                String.valueOf(quantity).hashCode() +
                String.valueOf(pricePerUnit).hashCode() +
                user.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if(o == null) {
            return false;
        }

        if(!Bid.class.isAssignableFrom(o.getClass())) {
            return false;
        }

        final Bid bid = (Bid)o;

        return type.equals(bid.type) && actionId.equals(bid.actionId);
    }

    public boolean matches(Offer matchable) {
         return itemId.equals(matchable.getItemId()) &&
                 quantity <= matchable.getQuantity() &&
                 pricePerUnit >= matchable.getPricePerUnit();
    }
}
