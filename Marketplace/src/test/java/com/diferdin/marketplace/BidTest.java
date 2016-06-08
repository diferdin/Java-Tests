package com.diferdin.marketplace;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by LONADF on 08/06/2016.
 */

public class BidTest {

    @Test
    public void shouldCreateBid() {
        Bid bid = new Bid("12345", 10, 25, "1");

        assertEquals(bid.getItemId(), "12345");
        assertEquals(bid.getQuantity(), 10);
        assertEquals(bid.getPricePerUnit(), 25);
        assertEquals(bid.getUser(), "1");
    }

    @Test
    public void bidsShouldNotBeEqual() {
        Bid bid1 = new Bid("12345", 10, 25, "1");
        Bid bid2 = new Bid("12344", 10, 25, "1");

        assertNotEquals(bid1, bid2);
    }

    @Test
    public void bidsShouldBeEqual() {
        Bid bid1 = new Bid("12345", 10, 25, "1");
        Bid bid2 = bid1.clone();

        assertEquals(bid1, bid2);
    }
}
