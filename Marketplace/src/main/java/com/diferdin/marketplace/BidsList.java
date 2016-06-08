package com.diferdin.marketplace;

import java.util.*;

/**
 * Created by LONADF on 08/06/2016.
 */
public class BidsList {
    private List<Bid> bids;

    public boolean add(Bid bid) {
        if(bids == null) {
            bids = new ArrayList<>();
        }

        if(bids.contains(bid)) {
            bids.replaceAll(b -> b.equals(bid) ? bid : b);
            return true;
        }

        return bids.add(bid);
    }

    public int size() {
        return bids.size();
    }

    public Optional<Bid> get(String bidId) {
        for(Bid bid : bids) {
            if(bidId.equals(bid.getBidId())) {
                return Optional.of(bid);
            }
        }
        return Optional.empty();
    }

    public Bid get(int index) {
        return bids.get(index);
    }

    public boolean remove(Bid bidToRemove) {
        for(Bid bid : bids) {
            if(bidToRemove.equals(bid)) {
                return bids.remove(bid);
            }
        }

        return false;
    }
}
