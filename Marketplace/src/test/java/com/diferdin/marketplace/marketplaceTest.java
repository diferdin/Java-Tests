package com.diferdin.marketplace;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by LONADF on 08/06/2016.
 */
public class marketplaceTest {

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

        assertEquals(1, marketplace.getBidsList().size());
        assertEquals(bid, marketplace.getBid(bid.getBidId()).get());
    }

    @Test
    public void shouldOverwriteBid() throws InterruptedException {
        Bid bid1 = new Bid("12345", 10, 25, "1");
        marketplace.addBid(bid1);

        Thread.sleep(5);

        Bid bid2 = new Bid("12345", 10, 25, "1");
        marketplace.addBid(bid2);

        assertEquals(1, marketplace.getBidsList().size());
        assertEquals(bid2.getTimestamp(), marketplace.getBidsList().get(0).getTimestamp());
    }

    @Test
    public void shouldRemoveBid() {
        Bid bid1 = new Bid("12345", 10, 25, "1");
        marketplace.addBid(bid1);

        marketplace.removeBid(bid1);

        assertEquals(0, marketplace.getBidsList().size());
    }

    @Test
    public void shouldNotRemoveBid() {
        Bid bid1 = new Bid("12345", 10, 25, "1");
        marketplace.addBid(bid1);

        Bid bid2 = new Bid("12344", 10, 25, "1");

        boolean removed = marketplace.removeBid(bid2);

        assertFalse(removed);
        assertEquals(1, marketplace.getBidsList().size());
    }
}
