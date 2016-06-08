package com.diferdin.marketplace;

import java.util.List;
import java.util.Optional;

/**
 * Created by LONADF on 08/06/2016.
 */

public class Marketplace {
    private BidsList bidsList;

    public Marketplace() {

    }

    public boolean addBid(Bid bid) {
        if(bidsList == null) {
            bidsList = new BidsList();
        }

        return bidsList.add(bid);
    }

    public BidsList getBidsList() {
        return bidsList;
    }

    public Optional<Bid> getBid(String bidId) {
        return bidsList.get(bidId);
    }

    public boolean removeBid(Bid bid) {
        return bidsList.remove(bid);
    }
}
