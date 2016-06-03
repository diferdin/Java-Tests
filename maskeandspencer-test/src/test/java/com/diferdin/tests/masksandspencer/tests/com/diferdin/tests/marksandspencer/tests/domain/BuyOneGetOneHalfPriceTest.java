package com.diferdin.tests.masksandspencer.tests.com.diferdin.tests.marksandspencer.tests.domain;

import com.diferdin.tests.masksandspencer.com.diferdin.tests.marksandspencer.domain.BuyOneGetOneHalfPrice;
import com.diferdin.tests.masksandspencer.com.diferdin.tests.marksandspencer.domain.Product;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by antonio on 01/06/2016.
 */

public class BuyOneGetOneHalfPriceTest {

    @Test
    public void shouldProvideDiscount() {
        List<Product> shoppingList = new LinkedList<>();

        shoppingList.add(new Product("Jeans", "J01", 32.95));
        shoppingList.add(new Product("Blouse", "B01", 24.95));
        shoppingList.add(new Product("Blouse", "B01", 24.95));
        shoppingList.add(new Product("Jeans", "J01", 32.95));
        shoppingList.add(new Product("Socks", "S01", 7.95));
        shoppingList.add(new Product("Jeans", "J01", 32.95));
        shoppingList.add(new Product("Blouse", "B01", 24.95));
        shoppingList.add(new Product("Socks", "S01", 7.95));
        shoppingList.add(new Product("Jeans", "J01", 32.95));
        shoppingList.add(new Product("Jeans", "J01", 32.95));
        shoppingList.add(new Product("Socks", "S01", 7.95));
        shoppingList.add(new Product("Socks", "S01", 7.95));
        shoppingList.add(new Product("Jeans", "J01", 32.95));
        shoppingList.add(new Product("Jeans", "J01", 32.95));

        BuyOneGetOneHalfPrice offer = new BuyOneGetOneHalfPrice("Jeans", "Buy one get one half price");

        double discount = offer.applyToList(shoppingList);

        assertEquals(49.42, discount, 0);
    }

    @Test
    public void shouldNotProvideDiscount() {
        List<Product> shoppingList = new LinkedList<>();

        shoppingList.add(new Product("Jeans", "J01", 32.95));
        shoppingList.add(new Product("Blouse", "B01", 24.95));
        shoppingList.add(new Product("Socks", "S01", 7.95));
        shoppingList.add(new Product("Blouse", "B01", 24.95));
        shoppingList.add(new Product("Socks", "S01", 7.95));
        shoppingList.add(new Product("Socks", "S01", 7.95));
        shoppingList.add(new Product("Socks", "S01", 7.95));

        BuyOneGetOneHalfPrice offer = new BuyOneGetOneHalfPrice("Jeans", "Buy one get one half price");

        double discount = offer.applyToList(shoppingList);

        assertEquals(0.0, discount, 0);
    }

}
