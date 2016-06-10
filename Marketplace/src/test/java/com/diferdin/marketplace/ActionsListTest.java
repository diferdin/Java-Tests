package com.diferdin.marketplace;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by LONADF on 10/06/2016.
 */
public class ActionsListTest {

    private ActionsList<Bid> bids;
    private ActionsList<Offer> offers;
    private ActionsList<Order> orders;

    @Before
    public void configureTest() {
        bids = new ActionsList<>();
        offers = new ActionsList<>();
        orders = new ActionsList<>();
    }

    @Test
    public void shouldAddAction() {
        Bid bid = new Bid("12345", 25, 23, "bidder");
        bids.add(bid);

        Offer offer = new Offer("23456", 21, 11, "offerer");
        offers.add(offer);

        Order order = new Order("12121", 10, 90, "bidder", "offerer");
        orders.add(order);

        assertEquals(1, bids.size());
        assertEquals(1, offers.size());
        assertEquals(1, orders.size());
    }

    @Test
    public void shouldGetActionByActionId() {
        Bid bid = new Bid("12345", 25, 23, "bidder");
        bids.add(bid);

        Offer offer = new Offer("23456", 21, 11, "offerer");
        offers.add(offer);

        Order order = new Order("12121", 2, 90, "bidder", "offerer");
        orders.add(order);

        Optional<Bid> retrievedBid = bids.get(bid.getId());
        Optional<Offer> retrievedOffer = offers.get(offer.getId());
        Optional<Order> retrievedOrder = orders.get(order.getId());

        assertTrue(retrievedBid.isPresent());
        assertTrue(retrievedOffer.isPresent());
        assertTrue(retrievedOrder.isPresent());

        assertEquals(bid, retrievedBid.get());
        assertEquals(offer, retrievedOffer.get());
        assertEquals(order, retrievedOrder.get());
    }

    @Test
    public void shouldRemoveAction() {
        Bid bid = new Bid("12345", 25, 23, "bidder");
        bids.add(bid);

        Offer offer = new Offer("23456", 21, 11, "offerer");
        offers.add(offer);

        Order order = new Order("12121", 2, 90, "bidder", "offerer");
        orders.add(order);

        bids.remove(bid);
        offers.remove(offer);
        orders.remove(order);

        assertEquals(0, bids.size());
        assertEquals(0, offers.size());
        assertEquals(0, orders.size());
    }

    @Test
    public void shouldIncreaseQuantity() {
        Bid bid = new Bid("12345", 25, 23, "bidder");
        bids.add(bid);

        Offer offer = new Offer("23456", 21, 11, "offerer");
        offers.add(offer);

        Order order = new Order("12121", 2, 90, "bidder", "offerer");
        orders.add(order);

        Bid bid2 = new Bid("12345", 25, 23, "bidder");
        bids.add(bid2);

        Offer offer2 = new Offer("23456", 21, 11, "offerer");
        offers.add(offer2);

        Order order2 = new Order("12121", 2, 90, "bidder", "offerer");
        orders.add(order2);

        assertEquals(1, bids.size());
        assertEquals(1, offers.size());
        assertEquals(1, orders.size());

        assertEquals(50, bids.get(bid.getId()).get().getQuantity());
        assertEquals(42, offers.get(offer.getId()).get().getQuantity());
        assertEquals(4, orders.get(order.getId()).get().getQuantity());
    }

    @Test
    public void shouldRetrieveActionsByUser() {
        Bid bid = new Bid("12345", 25, 23, "bidder");
        bids.add(bid);

        Offer offer = new Offer("23456", 21, 11, "offerer");
        offers.add(offer);

        Order order = new Order("12121", 2, 90, "bidder", "offerer");
        orders.add(order);

        assertEquals(1, bids.size());
        assertEquals(1, offers.size());
        assertEquals(1, offers.size());

        List<Bid> retrievedBid = bids.retrieveActionsByUser("bidder");
        assertEquals(1, retrievedBid.size());
        assertEquals(bid, retrievedBid.get(0));

        List<Offer> retrievedOffer = offers.retrieveActionsByUser("offerer");
        assertEquals(1, retrievedOffer.size());
        assertEquals(offer, retrievedOffer.get(0));

        List<Order> retrievedOrder = orders.retrieveActionsByUser("bidder");
        assertEquals(1, retrievedOrder.size());
        assertEquals(order, retrievedOrder.get(0));
    }

    @Test
    public void shouldRetrieveActionsByItemId() {
        Bid bid = new Bid("12345", 25, 23, "bidder");
        bids.add(bid);

        Offer offer = new Offer("23456", 21, 11, "offerer");
        offers.add(offer);

        Order order = new Order("12121", 2, 90, "bidder", "offerer");
        orders.add(order);

        assertEquals(1, bids.size());
        assertEquals(1, offers.size());
        assertEquals(1, offers.size());

        List<Bid> retrievedBid = bids.retrieveActionsByItemId("12345");
        assertEquals(1, retrievedBid.size());
        assertEquals(bid, retrievedBid.get(0));

        List<Offer> retrievedOffer = offers.retrieveActionsByItemId("23456");
        assertEquals(1, retrievedOffer.size());
        assertEquals(offer, retrievedOffer.get(0));

        List<Order> retrievedOrder = orders.retrieveActionsByItemId("12121");
        assertEquals(1, retrievedOrder.size());
        assertEquals(order, retrievedOrder.get(0));
    }

    @Test
    public void shouldContainAction() {
        Bid bid = new Bid("12345", 25, 23, "bidder");
        bids.add(bid);

        Offer offer = new Offer("23456", 21, 11, "offerer");
        offers.add(offer);

        Order order = new Order("12121", 2, 90, "bidder", "offerer");
        orders.add(order);

        assertTrue(bids.contains(bid));
        assertTrue(offers.contains(offer));
        assertTrue(orders.contains(order));
    }

    @Test
    public void shouldDecreaseQuantity() {
        Offer offer = new Offer("23456", 21, 11, "offerer");
        offers.add(offer);

        offers.decreaseQuantity(offer, 10);

        assertEquals(1, offers.size());
        assertEquals(11, offers.get(offer.getId()).get().getQuantity());
    }

    @Test
    public void shouldGetMaxPriceForItemId() {
        Bid bid = new Bid("12345", 25, 23, "bidder");
        bids.add(bid);

        Offer offer = new Offer("23456", 21, 11, "offerer");
        offers.add(offer);

        Bid bid2 = new Bid("12345", 25, 90, "bidder");
        bids.add(bid2);

        Offer offer2 = new Offer("23456", 21, 7, "offerer");
        offers.add(offer2);

        assertEquals(2, bids.size());
        assertEquals(2, offers.size());

        assertEquals(90, bids.getMaxPriceForItemId(bid.getItemId()));
        assertEquals(11, offers.getMaxPriceForItemId(offer.getItemId()));
    }
}
