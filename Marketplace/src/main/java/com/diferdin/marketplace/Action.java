package com.diferdin.marketplace;

import com.diferdin.marketplace.exception.ActionException;

import java.time.LocalDateTime;

/**
 * Created by LONADF on 09/06/2016.
 */
public abstract class Action implements Cloneable {
    protected String itemId;
    protected int quantity;
    protected int pricePerUnit;
    protected String user;
    protected LocalDateTime timestamp;
    protected String actionId;
    protected final ActionType type;

    public Action(ActionType type, String itemId, int quantity, int pricePerUnit, String user) {
        if(quantity <= 0) {
            throw new ActionException("Quantity cannot be 0");
        }

        if(pricePerUnit < 0) {
            throw new ActionException("Price per unit cannot be negative");
        }

        if(user == null || user.isEmpty() || itemId == null || itemId.isEmpty()) {
            throw new ActionException("User and item ID have to be provided");
        }



        this.itemId = itemId;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.user = user;
        this.type = type;

        timestamp = LocalDateTime.now();
        actionId = createActionId();
    }

    public ActionType getType() {
        return type;
    }

    public String getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPricePerUnit() {
        return pricePerUnit;
    }

    public String getUser() {
        return user;
    }

    public boolean setQuantity(int quantity) {
        if(quantity <= 0) {
            return false;
        }

        this.quantity = quantity;

        return true;
    }

    public boolean setPricePerUnit(int pricePerUnit) {
        if(pricePerUnit < 0) {
            return false;
        }

        this.pricePerUnit = pricePerUnit;
        return true;
    }

    public String getId() {
        return actionId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public boolean setTimestamp(LocalDateTime timestamp) {

        if(timestamp.isBefore(this.timestamp)) {
            return false;
        }

        this.timestamp = timestamp;

        return true;
    }

    private String createActionId() {
        return type +
                "_" +
                user +
                "_" +
                itemId +
                "_" +
                quantity+
                "_" +
                pricePerUnit;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }

        if(!Action.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        final Action action = (Action)obj;

        return type.equals(action.getType()) &&
                actionId.equals(action.getId()) &&
                pricePerUnit == action.getPricePerUnit() &&
                itemId.equals(action.getItemId()) &&
                user.equals(action.getUser());
    }

    @Override
    public int hashCode() {
        return type.hashCode() +
                itemId.hashCode() +
                String.valueOf(quantity).hashCode() +
                String.valueOf(pricePerUnit).hashCode() +
                user.hashCode();
    }
}
