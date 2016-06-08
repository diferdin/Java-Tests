package com.diferdin.marketplace;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by LONADF on 08/06/2016.
 */

public class Bid {
    private LocalDateTime timestamp;
    private String bidId;
    private String itemId;
    private int quantity;
    private int pricePerUnit;
    private String user;

    public Bid(String itemId, int quantity, int pricePerUnit, String user) {
        this.itemId = itemId;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.user = user;
        timestamp = LocalDateTime.now();
        bidId = createBidId();
    }

    private String createBidId() {
        return user +
                "_" +
                itemId +
                "_" +
                quantity+
                " " +
                pricePerUnit;
    }

    public String getBidId() {
        return bidId;
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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPricePerUnit(int pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public int hashCode() {
        return itemId.hashCode() +
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

        return bidId.equals(bid.bidId);
    }

    public Bid clone() {
        Bid newBid = new Bid(itemId, quantity, pricePerUnit, user);
        newBid.timestamp = timestamp;
        newBid.bidId = bidId;

        return newBid;
    }
}
