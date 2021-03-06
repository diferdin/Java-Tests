package com.diferdin.marketplace;

import com.diferdin.marketplace.exception.OrderException;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by LONADF on 08/06/2016.
 */

public class Marketplace {
    private ActionsList<Bid> bidsList;
    private ActionsList<Offer> offersList;
    private ActionsList<Order> ordersList;

    public Marketplace() {
        bidsList = new ActionsList<>();
        offersList = new ActionsList<>();
        ordersList = new ActionsList<>();
    }

    public boolean addBid(Bid bid) {

        if (bidMatchesOffers(bid)) {
            Optional<Offer> matchingOfferOptional = getMatchingOffer(bid);
            if (!matchingOfferOptional.isPresent()) {
                throw new OrderException("Could not find matching offer.");
            }
            Offer matchingOffer = matchingOfferOptional.get();

            return manageMatch(bid, matchingOffer);
        }

        return bidsList.add(bid);
    }

    public boolean addOffer(Offer originalOffer) {
        boolean offerAdded = offersList.add(originalOffer);

        Optional<Offer> optionalOffer = offersList.get(originalOffer.getId());

        if(!optionalOffer.isPresent()) {
            throw new OrderException("Cannot find offer");
        }

        Offer offer = optionalOffer.get();

        if (offerMatchesBids(offer)) {
            Optional<Bid> matchingBidOptional = getMatchingBid(offer);

            if (!matchingBidOptional.isPresent()) {
                throw new OrderException("Could not find matching bid");
            }

            Bid matchingBid = matchingBidOptional.get();

            return manageMatch(matchingBid, originalOffer);
        }

        return offerAdded;
    }

    private boolean manageMatch(Bid bid, Offer offer) {
        Order order = createOrder(bid, offer);

        if (!ordersList.add(order)) {
            throw new OrderException("Could not create order");
        }

        if (!decreaseOrRemoveOffer(bid, offer)) {
            throw new OrderException("Could not create order");
        }

        if(bidsList.contains(bid)) {
            removeBid(bid);
        }

        return true;
    }

    private boolean decreaseOrRemoveOffer(Bid bid, Offer offer) {
        return offersList.decreaseQuantity(offer, bid.getQuantity());
    }

    private Order createOrder(Bid bid, Offer offer) {
        return new Order(bid.getItemId(), bid.getQuantity(), offer.getPricePerUnit(), bid.getUser(), offer.getUser());
    }

    private Optional<Offer> getMatchingOffer(Bid bid) {
        List<Offer> offersOnItem = offersList.retrieveActionsByItemId(bid.getItemId());

        if (offersOnItem.size() == 0) {
            return Optional.empty();
        }

        List<Offer> matchingOffers = offersOnItem.stream()
                .filter(o -> o.getPricePerUnit() <= bid.getPricePerUnit())
                .filter(o -> o.getQuantity() >= bid.getQuantity())
                .sorted((o1, o2) -> o1.getTimestamp().compareTo(o2.getTimestamp())).collect(Collectors.toList());

        if (matchingOffers.size() == 0) {
            return Optional.empty();
        }

        return Optional.of(matchingOffers.get(0));
    }

    private Optional<Bid> getMatchingBid(Offer offer) {
        List<Bid> bidsOnItem = bidsList.retrieveActionsByItemId(offer.getItemId());

        if (bidsOnItem.size() == 0) {
            return Optional.empty();
        }

        List<Bid> matchingBids = bidsOnItem.stream()
                .filter(b -> b.getPricePerUnit() >= offer.getPricePerUnit())
                .filter(b -> b.getQuantity() <= offer.getQuantity())
                .sorted((b1, b2) -> b1.getTimestamp().compareTo(b2.getTimestamp())).collect(Collectors.toList());

        if (matchingBids.size() == 0) {
            return Optional.empty();
        }

        return Optional.of(matchingBids.get(0));
    }

    private boolean bidMatchesOffers(Bid bid) {
        Optional<Offer> matchingOffer = getMatchingOffer(bid);

        return matchingOffer.isPresent();

    }

    private boolean offerMatchesBids(Offer offer) {
        Optional<Bid> matchingBid = getMatchingBid(offer);

        return matchingBid.isPresent();

    }

    public ActionsList<Bid> getBids() {
        return bidsList;
    }

    public ActionsList<Offer> getOffers() {
        return offersList;
    }

    public Optional<Bid> getBid(String bidId) {
        return bidsList.get(bidId);
    }

    public Optional<Offer> getOfferById(String offerId) {
        return offersList.get(offerId);
    }

    public List<Offer> getOfferByitem(String itemId) {
        return offersList.retrieveActionsByItemId(itemId);
    }

    public boolean removeBid(Bid bid) {
        return bidsList.remove(bid);
    }

    public boolean removeOffer(Offer offer) {
        return offersList.remove(offer);
    }

    public List<Bid> getBids(String bidderId) {
        return bidsList.retrieveActionsByUser(bidderId);
    }

    public List<Offer> getOffers(String offererId) {
        return offersList.retrieveActionsByUser(offererId);
    }

    public ActionsList<Order> getOrders() {
        return ordersList;
    }

    public List<Order> getOrdersByBuyerId(String bidderId) {
        return ordersList.retrieveActionsByUser(bidderId);
    }

    public List<Order> getOrdersByOffererId(String offererId) {

        return ordersList.getAll()
                .stream().filter(o -> o.getOtherPartyId().equals(offererId))
                .collect(Collectors.toList());
    }

    public int getMaxBidPriceForItemId(String itemId) {
        return bidsList.getMaxPriceForItemId(itemId);
    }

    public int getMaxOfferPriceForItemId(String itemId) {
        return offersList.getMaxPriceForItemId(itemId);
    }
}
