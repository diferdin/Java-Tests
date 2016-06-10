package com.diferdin.marketplace;

import com.diferdin.marketplace.exception.ActionException;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

/**
 * Created by LONADF on 08/06/2016.
 */

public class ActionTest {

    @Test
    public void shouldCreateBidAndOffer() {
        Bid bid = new Bid("12345", 10, 25, "1");
        Offer offer = new Offer("23456", 10, 25, "2");

        assertEquals("12345", bid.getItemId());
        assertEquals(10, bid.getQuantity());
        assertEquals(25, bid.getPricePerUnit());
        assertEquals("1", bid.getUser());
        assertEquals("BID", bid.getType().getType());

        assertEquals("23456", offer.getItemId());
        assertEquals(10, offer.getQuantity());
        assertEquals(25, offer.getPricePerUnit());
        assertEquals("2", offer.getUser());
        assertEquals("OFFER", offer.getType().getType());
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

    @Test(expected = ActionException.class)
    public void shouldNotCreateWithZeroQuantity() {
        Bid bid = new Bid("11111", 0, 23, "user");
    }

    @Test(expected = ActionException.class)
    public void shouldNotCreateWithNullItemId() {
        Bid bid = new Bid(null, 12, 23, "user");
    }

    @Test(expected = ActionException.class)
    public void shouldNotCreateWithEmptyItemId() {
        Bid bid = new Bid("", 12, 23, "user");
    }

    @Test(expected = ActionException.class)
    public void shouldNotCreateWithNegativePrice() {
        Bid bid = new Bid("11111", 12, -23, "user");
    }

    @Test(expected = ActionException.class)
    public void shouldNotCreateWithAnonymousUser() {
        Bid bid = new Bid("11111", 12, 23, null);
    }

    @Test
    public void shouldNotSetTimestampBeforeCurrent() {
        Bid bid = new Bid("11111", 12, 23, "user");

        assertFalse(bid.setTimestamp(LocalDateTime.of(2000, 12, 13, 10, 12, 00)));
    }

    @Test
    public void shouldNotSetNegativePricePerUnit() {
        Offer offer = new Offer("33445", 31, 40, "user");

        assertFalse(offer.setPricePerUnit(-9));
    }

    @Test
    public void shouldCreatePredictableActionId() {
        Offer offer = new Offer("12234", 12, 90, "user");

        assertEquals("OFFER_user_12234_12_90", offer.getId());
    }
}
