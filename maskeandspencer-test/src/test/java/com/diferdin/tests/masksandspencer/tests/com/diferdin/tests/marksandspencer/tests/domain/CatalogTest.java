package com.diferdin.tests.masksandspencer.tests.com.diferdin.tests.marksandspencer.tests.domain;

import com.diferdin.tests.masksandspencer.com.diferdin.tests.marksandspencer.domain.Catalog;
import com.diferdin.tests.masksandspencer.com.diferdin.tests.marksandspencer.domain.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by antonio on 01/06/2016.
 */

public class CatalogTest {

    private Catalog catalog;

    @Before
    public void configure() {
        buildCatalog();
    }

    @Test
    public void shouldGetProducts() {

        Product jeans = new Product("Jeans", "J01", 32.95);
        Product blouse = new Product("Blouse", "B01", 24.95);
        Product socks = new Product("Socks", "S01", 7.95);


        assertEquals(catalog.size(), 3);
        assertEquals(catalog.get(jeans.getName()).get(), jeans);
        assertEquals(catalog.get(blouse.getName()).get(), blouse);
        assertEquals(catalog.get(socks.getName()).get(), socks);
    }

    @Test
    public void shouldNotGetProducts() {
        assertEquals(catalog.get("Trousers"), Optional.empty());
        assertEquals(catalog.get("J02"), Optional.empty());
    }

    @Test
    public void shouldContainProducts() {
        Product jeans = new Product("Jeans", "J01", 32.95);

        assertTrue(catalog.contains(jeans));
        assertTrue(catalog.contains(jeans.getName()));
    }

    @Test
    public void shouldNotContainProducts() {
        Product apple = new Product("Apple", "A01", 0.50);
        assertFalse(catalog.contains(apple));
        assertFalse(catalog.contains(apple.getName()));
    }

    private void buildCatalog() {
        List<Product> products = new LinkedList<>();

        products.add(new Product("Jeans", "J01", 32.95));
        products.add(new Product("Blouse", "B01", 24.95));
        products.add(new Product("Socks", "S01", 7.95));

        catalog = new Catalog(products);
    }
}
