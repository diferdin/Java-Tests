package com.diferdin.marketplace;

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
    protected final String type;

    public Action(String type, String itemId, int quantity, int pricePerUnit, String user) {
        this.itemId = itemId;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.user = user;
        this.type = type;

        timestamp = LocalDateTime.now();
        actionId = createActionId();
    }

    public String getType() {
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
        this.quantity = quantity;
        return true;
    }

    public boolean setPricePerUnit(int pricePerUnit) {
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
                " " +
                pricePerUnit;
    }
}
