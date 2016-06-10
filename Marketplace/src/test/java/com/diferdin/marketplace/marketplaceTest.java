package com.diferdin.marketplace;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by LONADF on 08/06/2016.
 */
public class MarketplaceTest {

    private Marketplace marketplace;

    @Before
    public void configureTest() {
        marketplace = new Marketplace();
    }

    @Test
    public void testMarketplace() {
        assertNotNull(marketplace);
    }

    @Test
    public void shouldAddBid() {
        Bid bid = new Bid("12345", 10, 25, "1");
        marketplace.addBid(bid);

        assertEquals(1, marketplace.getBids().size());
        assertEquals(bid, marketplace.getBid(bid.getId()).get());
    }

    @Test
    public void shouldAddOffer() {
        Offer offer = new Offer("12345", 10, 25, "1");
        marketplace.addOffer(offer);

        assertEquals(1, marketplace.getOffers().size());
        assertEquals(offer, marketplace.getOfferById(offer.getId()).get());
    }

    @Test
    public void shouldIncreaseBidQuantity() throws InterruptedException {
        Bid bid1 = new Bid("12345", 10, 25, "1");
        marketplace.addBid(bid1);

        Thread.sleep(5);

        Bid bid2 = new Bid("12345", 10, 25, "1");
        marketplace.addBid(bid2);

        assertEquals(1, marketplace.getBids().size());
        assertEquals(bid2.getTimestamp(), marketplace.getBids().get(0).getTimestamp());
        assertEquals(20, marketplace.getBid(bid1.getId()).get().getQuantity());
    }

    @Test
    public void shouldIncreaseOfferQuantity() throws InterruptedException {
        Offer offer1 = new Offer("12345", 10, 25, "1");
        marketplace.addOffer(offer1);

        Thread.sleep(5);

        Offer offer2 = new Offer("12345", 10, 25, "1");
        marketplace.addOffer(offer2);

        assertEquals(1, marketplace.getOffers().size());
        assertEquals(offer2.getTimestamp(), marketplace.getOffers().get(0).getTimestamp());
        assertEquals(20, marketplace.getOfferById(offer1.getId()).get().getQuantity());
    }

    @Test
    public void shouldDecreaseOfferQuantity() throws InterruptedException {
        Bid bid = new Bid("12345", 5, 30, "1");
        marketplace.addBid(bid);

        Offer offer = new Offer("12345", 10, 25, "1");
        marketplace.addOffer(offer);

        assertEquals(1, marketplace.getOrders().size());
        assertEquals(0, marketplace.getBids().size());
        assertEquals(1, marketplace.getOffers().size());
        assertEquals(5, marketplace.getOfferById(offer.getId()).get().getQuantity());
    }

    @Test
    public void shouldRemoveBid() {
        Bid bid1 = new Bid("12345", 10, 25, "1");
        marketplace.addBid(bid1);

        boolean removed = marketplace.removeBid(bid1);

        assertTrue(removed);
        assertEquals(0, marketplace.getBids().size());
    }

    @Test
    public void shouldRemoveOffer() {
        Offer offer1 = new Offer("12345", 10, 25, "1");
        marketplace.addOffer(offer1);

        boolean removed = marketplace.removeOffer(offer1);

        assertTrue(removed);
        assertEquals(0, marketplace.getOffers().size());
    }

    @Test
    public void shouldNotRemoveBid() {
        Bid bid1 = new Bid("12345", 10, 25, "1");
        marketplace.addBid(bid1);

        Bid bid2 = new Bid("12344", 10, 25, "1");

        boolean removed = marketplace.removeBid(bid2);

        assertFalse(removed);
        assertEquals(1, marketplace.getBids().size());
    }

    @Test
    public void shouldNotRemoveOffer() {
        Offer offer1 = new Offer("12345", 10, 25, "1");
        marketplace.addOffer(offer1);

        Offer offer2 = new Offer("12344", 10, 25, "1");

        boolean removed = marketplace.removeOffer(offer2);

        assertFalse(removed);
        assertEquals(1, marketplace.getOffers().size());
    }

    @Test
    public void shouldReturnBidsByBidderId() {
        Bid bid1 = new Bid("12345", 10, 25, "1");
        marketplace.addBid(bid1);

        Bid bid2 = new Bid("12345", 10, 25, "2");
        marketplace.addBid(bid2);

        Bid bid3 = new Bid("12344", 10, 25, "1");
        marketplace.addBid(bid3);

        Bid bid4 = new Bid("12345", 10, 25, "3");
        marketplace.addBid(bid4);

        Bid bid5 = new Bid("12345", 10, 23, "1");
        marketplace.addBid(bid5);

        assertEquals(5, marketplace.getBids().size());

        List<Bid> userOneBids = marketplace.getBids(bid1.getUser());
        assertEquals(3, userOneBids.size());
    }

    @Test
    public void shouldReturnOffersByOffererId() {
        Offer offer1 = new Offer("12345", 10, 25, "1");
        marketplace.addOffer(offer1);

        Offer offer2 = new Offer("12345", 10, 25, "2");
        marketplace.addOffer(offer2);

        Offer offer3 = new Offer("12344", 10, 25, "1");
        marketplace.addOffer(offer3);

        Offer offer4 = new Offer("12345", 10, 25, "3");
        marketplace.addOffer(offer4);

        Offer offer5 = new Offer("12345", 10, 23, "1");
        marketplace.addOffer(offer5);

        assertEquals(5, marketplace.getOffers().size());

        List<Offer> userOneOffers = marketplace.getOffers(offer1.getUser());
        assertEquals(3, userOneOffers.size());
    }

    @Test
    public void shouldIncreaseQuantityOnOffer() {
        Offer offer1 = new Offer("12345", 10, 25, "1");
        marketplace.addOffer(offer1);

        Offer offer2 = new Offer("12345", 10, 25, "1");
        marketplace.addOffer(offer2);

        assertEquals(1, marketplace.getOffers().size());

        List<Offer> offers = marketplace.getOfferByitem("12345");

        assertEquals(1, offers.size());
        assertEquals(20, offers.get(0).getQuantity());
    }

    @Test
    public void shouldCreateOrderWithOfferFirst() {
        Offer offer1 = new Offer("12345", 10, 25, "1");
        marketplace.addOffer(offer1);

        Bid bid1 = new Bid("12345", 10, 25, "2");
        marketplace.addBid(bid1);

        assertEquals(0, marketplace.getBids().size());
        assertEquals(0, marketplace.getOffers().size());
        assertEquals(1, marketplace.getOrders().size());
    }

    @Test
    public void shouldCreateOrderWithBidFirst() {
        Bid bid1 = new Bid("12345", 10, 25, "2");
        marketplace.addBid(bid1);

        Offer offer1 = new Offer("12345", 10, 25, "1");
        marketplace.addOffer(offer1);

        assertEquals(0, marketplace.getBids().size());
        assertEquals(0, marketplace.getOffers().size());
        assertEquals(1, marketplace.getOrders().size());
    }

    @Test
    public void shouldNotMatchAnOrderForLowPrice() {
        Bid bid1 = new Bid("12345", 10, 25, "2");
        marketplace.addBid(bid1);

        Offer offer1 = new Offer("12345", 10, 30, "1");
        marketplace.addOffer(offer1);

        assertEquals(1, marketplace.getBids().size());
        assertEquals(1, marketplace.getOffers().size());
        assertEquals(0, marketplace.getOrders().size());
    }

    @Test
    public void shouldNotMatchAnOrderForLowQuantity() {
        Bid bid1 = new Bid("12345", 10, 25, "2");
        marketplace.addBid(bid1);

        Offer offer1 = new Offer("12345", 5, 25, "1");
        marketplace.addOffer(offer1);

        assertEquals(1, marketplace.getBids().size());
        assertEquals(1, marketplace.getOffers().size());
        assertEquals(0, marketplace.getOrders().size());
    }

    @Test
    public void shouldNotMatchAnOrderForWrongItemId() {
        Bid bid1 = new Bid("12345", 10, 25, "2");
        marketplace.addBid(bid1);

        Offer offer1 = new Offer("23456", 10, 25, "1");
        marketplace.addOffer(offer1);

        assertEquals(1, marketplace.getBids().size());
        assertEquals(1, marketplace.getOffers().size());
        assertEquals(0, marketplace.getOrders().size());
    }

    @Test
    public void shouldCreateOrderForCombinedOffers() {
        Bid bid1 = new Bid("12345", 10, 25, "2");
        marketplace.addBid(bid1);

        Offer offer1 = new Offer("12345", 5, 25, "1");
        marketplace.addOffer(offer1);
        Offer offer2 = new Offer("12345", 5, 25, "1");
        marketplace.addOffer(offer2);

        assertEquals(0, marketplace.getBids().size());
        assertEquals(0, marketplace.getOffers().size());
        assertEquals(1, marketplace.getOrders().size());
    }

    @Test
    public void shouldListOrdersByBidderId() {
        Bid bid1 = new Bid("12345", 10, 25, "2");
        Bid bid2 = new Bid("23456", 25, 30, "2");
        marketplace.addBid(bid1);
        marketplace.addBid(bid2);

        Offer offer1 = new Offer("12345", 10, 25, "2");
        Offer offer2 = new Offer("23456", 25, 30, "3");
        marketplace.addOffer(offer1);
        marketplace.addOffer(offer2);

        List<Order> orders = marketplace.getOrdersByBuyerId("2");

        assertEquals(2, orders.size());
    }

    @Test
    public void shouldListOrdersByOffererId() {
        Bid bid1 = new Bid("12345", 10, 25, "1");
        Bid bid2 = new Bid("23456", 25, 30, "2");
        marketplace.addBid(bid1);
        marketplace.addBid(bid2);

        Offer offer1 = new Offer("12345", 10, 25, "3");
        Offer offer2 = new Offer("23456", 25, 30, "3");
        marketplace.addOffer(offer1);
        marketplace.addOffer(offer2);

        List<Order> orders = marketplace.getOrdersByOffererId("3");

        assertEquals(2, orders.size());
    }

    @Test
    public void shouldRetrieveMaxCurrentBidPriceForGivenItemId() {
        Bid bid1 = new Bid("23456", 25, 21, "2");
        marketplace.addBid(bid1);

        Bid bid2 = new Bid("23456", 25, 34, "1");
        marketplace.addBid(bid2);

        Bid bid3 = new Bid("23456", 25, 16, "3");
        marketplace.addBid(bid3);

        Bid bid4 = new Bid("34567", 25, 9, "1");
        marketplace.addBid(bid4);

        Bid bid5 = new Bid("34567", 13, 48, "1");
        marketplace.addBid(bid5);

        assertEquals(5, marketplace.getBids().size());
        assertEquals(34, marketplace.getMaxBidPriceForItemId("23456"));
        assertEquals(48, marketplace.getMaxBidPriceForItemId("34567"));
    }

    @Test
    public void shouldNotFindMaxPriceForNonExistingItem() {
        Bid bid1 = new Bid("23456", 25, 21, "2");
        marketplace.addBid(bid1);

        Bid bid2 = new Bid("23456", 25, 34, "1");
        marketplace.addBid(bid2);

        assertEquals(2, marketplace.getBids().size());
        assertEquals(0, marketplace.getMaxBidPriceForItemId("56787"));
    }

    @Test
    public void shouldRetrieveMaxCurrentOfferPriceForGivenItemId() {
        Offer offer1 = new Offer("11111", 25, 21, "2");
        marketplace.addOffer(offer1);

        Offer offer2 = new Offer("22222", 25, 76, "1");
        marketplace.addOffer(offer2);

        Offer offer3 = new Offer("22222", 25, 16, "3");
        marketplace.addOffer(offer3);

        Offer offer4 = new Offer("22222", 25, 9, "1");
        marketplace.addOffer(offer4);

        Offer offer5 = new Offer("33333", 13, 48, "1");
        marketplace.addOffer(offer5);

        assertEquals(5, marketplace.getOffers().size());
        assertEquals(21, marketplace.getMaxOfferPriceForItemId("11111"));
        assertEquals(76, marketplace.getMaxOfferPriceForItemId("22222"));
    }

    @Test
    public void shouldMatchEarliestOffer() throws InterruptedException {
        Offer offer1 = new Offer("11111", 25, 21, "1");
        marketplace.addOffer(offer1);

        Thread.sleep(5);

        Offer offer2 = new Offer("11111", 25, 21, "2");
        marketplace.addOffer(offer2);

        Thread.sleep(5);

        Offer offer3 = new Offer("11111", 25, 21, "3");
        marketplace.addOffer(offer3);

        Bid bid = new Bid("11111", 25, 30, "4");
        marketplace.addBid(bid);

        assertEquals(2, marketplace.getOffers().size());
        assertEquals(1, marketplace.getOrders().size());
        assertEquals(0, marketplace.getBids().size());

        Order order = marketplace.getOrdersByOffererId(offer1.getUser()).get(0);

        assertEquals(offer1.getUser(), order.getOtherPartyId());
    }

    @Test
    public void shouldMatchEarliestBid() throws InterruptedException {
        Bid bid1 = new Bid("11111", 25, 21, "1");
        marketplace.addBid(bid1);

        Thread.sleep(5);

        Bid bid2 = new Bid("11111", 25, 21, "2");
        marketplace.addBid(bid2);

        Thread.sleep(5);

        Bid bid3 = new Bid("11111", 25, 21, "3");
        marketplace.addBid(bid3);

        Offer offer = new Offer("11111", 25, 20, "4");
        marketplace.addOffer(offer);

        assertEquals(2, marketplace.getBids().size());
        assertEquals(1, marketplace.getOrders().size());
        assertEquals(0, marketplace.getOffers().size());

        Order order = marketplace.getOrdersByOffererId(offer.getUser()).get(0);

        assertEquals(bid1.getUser(), order.getUser());
    }

    @Test
    public void shouldPairOrders() throws InterruptedException {
        Bid bid1 = new Bid("11111", 25, 21, "1");
        marketplace.addBid(bid1);

        Thread.sleep(5);

        Bid bid2 = new Bid("11111", 25, 21, "2");
        marketplace.addBid(bid2);

        Thread.sleep(5);

        Offer offer1 = new Offer("11111", 28, 20, "3");
        marketplace.addOffer(offer1);

        Thread.sleep(5);

        Offer offer2 = new Offer("11111", 32, 20, "4");
        marketplace.addOffer(offer2);

        assertEquals(0, marketplace.getBids().size());
        assertEquals(2, marketplace.getOffers().size());
        assertEquals(2, marketplace.getOrders().size());
    }

}
