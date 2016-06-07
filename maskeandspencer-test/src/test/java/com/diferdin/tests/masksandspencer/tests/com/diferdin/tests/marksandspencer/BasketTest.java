package com.diferdin.tests.masksandspencer.tests.com.diferdin.tests.marksandspencer;

import com.diferdin.tests.masksandspencer.*;
import com.diferdin.tests.masksandspencer.exception.ShoppingException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by LONADF on 02/06/2016.
 */

public class BasketTest {

    @Test
    public void testTotalWithDeliveryAndNoDiscount() {

        List<Product> shoppingList = new ArrayList<>();

        shoppingList.add(new Product("Jeans", "J01", 32.95));
        shoppingList.add(new Product("Socks", "S01", 7.95));
        shoppingList.add(new Product("Blouse", "B01", 24.95));

        Basket basket = new Basket(shoppingList);
        double total = basket.total();

        assertEquals(total, 68.8, 0);
    }

    @Test
    public void testTotalWithNoDeliveryAndDiscount() {

        List<Product> shoppingList = new ArrayList<>();

        shoppingList.add(new Product("Socks", "S01", 7.95));
        shoppingList.add(new Product("Jeans", "J01", 32.95));
        shoppingList.add(new Product("Blouse", "B01", 24.95));
        shoppingList.add(new Product("Socks", "S01", 7.95));
        shoppingList.add(new Product("Jeans", "J01", 32.95));
        shoppingList.add(new Product("Blouse", "B01", 24.95));
        shoppingList.add(new Product("Socks", "S01", 7.95));

        Basket basket = new Basket(shoppingList);
        double total = basket.total();

        assertEquals(total, 123.18, 0);
    }

    @Test
    public void shouldFlagEmptyShoppingList() {
        List<Product> shoppingList = new ArrayList<>();

        Basket basket = new Basket(shoppingList);
        assertEquals(basket.total(), 0.0, 0);
    }

    @Test(expected=ShoppingException.class)
    public void shouldThrowShoppingExceptionWhenCatalogIsEmpty() {
        Catalogs.eraseCatalogs();

        List<Product> shoppingList = new ArrayList<>();

        shoppingList.add(new Product("Socks", "S01", 7.95));

        Basket basket = new Basket(shoppingList);
    }

    @Before
    public void buildContextObjects() {
        Catalog catalog = buildCatalog();

        Offer buyOneGetOneHaldPrice = new BuyOneGetOneHalfPrice("Jeans", "Buy one get one free on Jeans");

        Charge deliveryCharge = buildDeliveryCharge();
    }

    private DeliveryCharge buildDeliveryCharge() {
        List<DeliveryChargeRule> rules = new ArrayList<>();

        rules.add(new DeliveryChargeRule(0, 50, 4.95));
        rules.add(new DeliveryChargeRule(50.01, 90, 2.95));
        rules.add(new DeliveryChargeRule(90.01, 0));

        return new DeliveryCharge(rules);
    }

    private Catalog buildCatalog() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Jeans", "J01", 32.95));
        products.add(new Product("Blouse", "B01", 24.95));
        products.add(new Product("Socks", "S01", 7.95));

        return new Catalog(products);
    }
}
