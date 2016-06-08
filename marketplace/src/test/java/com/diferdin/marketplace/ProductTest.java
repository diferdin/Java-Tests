package com.diferdin.marketplace;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by antonio on 01/06/2016.
 */

public class ProductTest {

    @Test
    public void productTest() {

        Product product = new Product("Jeans", "J01", 32.95);
        assertEquals(product.toString(), "Name: Jeans, Code: J01, Price: 32.95 ");

    }


    @Test
    public void productsShouldBeEqual() {

        Product product1 = new Product("Jeans", "J01", 32.95);
        Product product2 = product1;
        Product product3 = new Product("Jeans", "J01", 32.95);

        assertTrue(product1.equals(product2));
        assertTrue(product2.equals(product3));
        assertTrue(product1.equals(product3));
    }

    @Test
    public void productsShouldNotBEEqual() {
        Product product1 = new Product("Jeans", "J01", 32.95);
        Product product2 = new Product("Jeans", "J02", 32.95);
        Product product3 = new Product("Jeans", "J01", 46.95);

        assertFalse(product1.equals(product2));
        assertFalse(product2.equals(product3));
        assertFalse(product1.equals(product3));
    }
}
