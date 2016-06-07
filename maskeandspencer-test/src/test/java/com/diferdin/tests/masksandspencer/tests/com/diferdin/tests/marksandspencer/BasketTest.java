package com.diferdin.tests.masksandspencer.tests.com.diferdin.tests.marksandspencer;

import com.diferdin.tests.masksandspencer.*;
import com.diferdin.tests.masksandspencer.exception.ShoppingException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by LONADF on 02/06/2016.
 */

public class BasketTest {

    private ShoppingList shoppingList;
    private Catalog productCatalog;
    private List<Offer> offers;
    DeliveryCharge deliveryCharge;

    @Before
    public void configureTest() {
        shoppingList = new ShoppingList();
        productCatalog = buildCatalog();
        offers = buildOffers();
        deliveryCharge = buildDeliveryCharge();
    }

    @Test
    public void testTotalWithDeliveryAndNoDiscount() {

        shoppingList.add(new Product("Jeans", "J01", 32.95));
        shoppingList.add(new Product("Socks", "S01", 7.95));
        shoppingList.add(new Product("Blouse", "B01", 24.95));

        Basket basket = new Basket(shoppingList, productCatalog, offers, deliveryCharge);
        double total = basket.total();

        assertEquals(total, 68.8, 0);
    }

    @Test
    public void testTotalWithNoDeliveryAndDiscount() {

        shoppingList.add(new Product("Socks", "S01", 7.95));
        shoppingList.add(new Product("Jeans", "J01", 32.95));
        shoppingList.add(new Product("Blouse", "B01", 24.95));
        shoppingList.add(new Product("Socks", "S01", 7.95));
        shoppingList.add(new Product("Jeans", "J01", 32.95));
        shoppingList.add(new Product("Blouse", "B01", 24.95));
        shoppingList.add(new Product("Socks", "S01", 7.95));

        Basket basket = new Basket(shoppingList, productCatalog, offers, deliveryCharge);
        double total = basket.total();

        assertEquals(total, 123.18, 0);
    }

    @Test(expected=ShoppingException.class)
    public void shouldThrowShoppingExceptionWhenCatalogIsEmpty() {
        shoppingList.add(new Product("Socks", "S01", 7.95));
        productCatalog = null;

        Basket basket = new Basket(shoppingList, productCatalog, offers, deliveryCharge);
    }

    private DeliveryCharge buildDeliveryCharge() {
        List<DeliveryChargeRule> rules = new ArrayList<>();

        rules.add(new DeliveryChargeRule(0, 50, 4.95));
        rules.add(new DeliveryChargeRule(50.01, 90, 2.95));
        rules.add(new DeliveryChargeRule(90.01, 0));

        return new DeliveryCharge(rules);
    }

    private Catalog buildCatalog() {
        List<Product> products = new LinkedList<>();

        products.add(new Product("Jeans", "J01", 32.95));
        products.add(new Product("Blouse", "B01", 24.95));
        products.add(new Product("Socks", "S01", 7.95));

        return new Catalog(products);
    }

    private List<Offer> buildOffers() {
        List<Offer> offers = new LinkedList<>();
        Offer buyOneGetOneHaldPrice = new BuyOneGetOneHalfPrice("J01", "Buy one get one free on Jeans");
        offers.add(buyOneGetOneHaldPrice);

        return offers;
    }
}
