package com.diferdin.marketplace;

/**
 * Created by LONADF on 09/06/2016.
 */
public class Offer extends Action {

    private static final String OFFER_TYPE = "OFFER";

    public Offer(String itemId, int quantity, int pricePerUnit, String user) {
        super(OFFER_TYPE, itemId, quantity, pricePerUnit, user);
    }

    @Override
    public int hashCode() {
        return OFFER_TYPE.hashCode() +
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

        if(!Offer.class.isAssignableFrom(o.getClass())) {
            return false;
        }

        final Offer offer = (Offer)o;

        return type.equals(offer.type) && actionId.equals(offer.actionId);
    }

    public boolean matches(Bid matchable) {
        return itemId.equals(matchable.getItemId()) &&
                quantity >= matchable.getQuantity() &&
                pricePerUnit <= matchable.getPricePerUnit();
    }
}
