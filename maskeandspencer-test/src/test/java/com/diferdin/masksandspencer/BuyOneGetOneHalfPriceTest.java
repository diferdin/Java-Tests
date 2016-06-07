package com.diferdin.masksandspencer;

import com.diferdin.masksandspencer.BuyOneGetOneHalfPrice;
import com.diferdin.masksandspencer.Product;
import com.diferdin.masksandspencer.ShoppingList;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by antonio on 01/06/2016.
 */

public class BuyOneGetOneHalfPriceTest {

    @Test
    public void shouldProvideDiscount() {
        ShoppingList shoppingList = new ShoppingList();

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

        BuyOneGetOneHalfPrice offer = new BuyOneGetOneHalfPrice("J01", "Buy one get one half price");

        double discount = offer.applyToList(shoppingList);

        assertEquals(49.42, discount, 0);
    }

    @Test
    public void shouldNotProvideDiscount() {
        ShoppingList shoppingList = new ShoppingList();

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
