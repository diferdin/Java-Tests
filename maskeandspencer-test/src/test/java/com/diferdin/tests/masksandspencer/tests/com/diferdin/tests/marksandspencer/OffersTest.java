package com.diferdin.tests.masksandspencer.tests.com.diferdin.tests.marksandspencer;

import com.diferdin.tests.masksandspencer.*;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by LONADF on 02/06/2016.
 */

public class OffersTest {

    @Before
    public void addOffers() {
        Offers.add(new BuyOneGetOneHalfPrice("Jeans", "Buy one get one half price on jeans"));
        //Offers.add(new BuyOneGetOneHalfPrice("Jumper", "Buy one get one half price on jumper"));
    }

    @Test
    public void shouldGetForCatalog() {
        Catalog catalog = buildCatalog();

        List<Offer> retrieved = Offers.getForCatalog(catalog);

        assertNotNull(retrieved);
        assertEquals(1, retrieved.size());
        Offers.erase();
    }

    @Test
    public void shouldNotGetForCatalog() {

        Catalog catalog = new Catalog();
        catalog.addProduct(new Product("Jumper", "JP01", 45.90));

        List<Offer> retrieved = Offers.getForCatalog(catalog);

        assertNotNull(retrieved);
        assertEquals(0, retrieved.size());
        Offers.erase();
    }

    private Catalog buildCatalog() {
        List<Product> products = new LinkedList<>();

        products.add(new Product("Jeans", "J01", 32.95));
        products.add(new Product("Blouse", "B01", 24.95));
        products.add(new Product("Socks", "S01", 7.95));

        return new Catalog(products);
    }
}
