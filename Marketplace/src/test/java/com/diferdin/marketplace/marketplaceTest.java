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
    public void shouldOverwriteBid() throws InterruptedException {
        Bid bid1 = new Bid("12345", 10, 25, "1");
        marketplace.addBid(bid1);

        Thread.sleep(5);

        Bid bid2 = new Bid("12345", 10, 25, "1");
        marketplace.addBid(bid2);

        assertEquals(1, marketplace.getBids().size());
        assertEquals(bid2.getTimestamp(), marketplace.getBids().get(0).getTimestamp());
    }

    @Test
    public void shouldOverwriteOffer() throws InterruptedException {
        Offer offer1 = new Offer("12345", 10, 25, "1");
        marketplace.addOffer(offer1);

        Thread.sleep(5);

        Offer offer2 = new Offer("12345", 10, 25, "1");
        marketplace.addOffer(offer2);

        assertEquals(1, marketplace.getOffers().size());
        assertEquals(offer2.getTimestamp(), marketplace.getOffers().get(0).getTimestamp());
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

}
