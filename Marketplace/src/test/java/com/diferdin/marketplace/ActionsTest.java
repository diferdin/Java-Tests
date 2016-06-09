package com.diferdin.marketplace;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by LONADF on 08/06/2016.
 */

public class ActionsTest {

    @Test
    public void shouldCreateBidAndOffer() {
        Bid bid = new Bid("12345", 10, 25, "1");
        Offer offer = new Offer("23456", 10, 25, "2");

        assertEquals("12345", bid.getItemId());
        assertEquals(10, bid.getQuantity());
        assertEquals(25, bid.getPricePerUnit());
        assertEquals("1", bid.getUser());
        assertEquals("BID", bid.getType());

        assertEquals("23456", offer.getItemId());
        assertEquals(10, offer.getQuantity());
        assertEquals(25, offer.getPricePerUnit());
        assertEquals("2", offer.getUser());
        assertEquals("OFFER", offer.getType());
    }

    @Test
    public void actionsShouldNotBeEqual() {
        Bid bid1 = new Bid("12345", 10, 25, "1");
        Bid bid2 = new Bid("12344", 10, 25, "1");

        Offer offer1 = new Offer("12345", 10, 25, "1");
        Offer offer2 = new Offer("12345", 10, 25, "2");

        assertNotEquals(bid1, bid2);
        assertNotEquals(offer1, offer2);
        assertNotEquals(bid1, offer1);
    }
}
