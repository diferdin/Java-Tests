package com.diferdin.marketplace;

/**
 * Created by LONADF on 09/06/2016.
 */
public class Order extends Action {

    private final String otherPartyId;

    public Order(String itemId, int quantity, int pricePerUnit, String user, String otherPartyId) {
        super(ActionType.ORDER, itemId, quantity, pricePerUnit, user);
        this.otherPartyId = otherPartyId;
    }

    public String getOtherPartyId() {
        return otherPartyId;
    }

    @Override
    public int hashCode() {
        return ActionType.OFFER.hashCode() +
                itemId.hashCode() +
                String.valueOf(quantity).hashCode() +
                String.valueOf(pricePerUnit).hashCode() +
                user.hashCode() +
                otherPartyId.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if(o == null) {
            return false;
        }

        if(!Order.class.isAssignableFrom(o.getClass())) {
            return false;
        }

        final Order order = (Order)o;

        return type.equals(order.type) &&
                actionId.equals(order.actionId) &&
                otherPartyId.equals(order.otherPartyId);
    }
}
