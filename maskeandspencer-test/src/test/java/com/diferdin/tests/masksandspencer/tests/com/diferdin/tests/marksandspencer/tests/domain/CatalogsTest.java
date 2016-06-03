package com.diferdin.tests.masksandspencer.tests.com.diferdin.tests.marksandspencer.tests.domain;

import com.diferdin.tests.masksandspencer.com.diferdin.tests.marksandspencer.domain.Catalog;
import com.diferdin.tests.masksandspencer.com.diferdin.tests.marksandspencer.domain.Catalogs;
import com.diferdin.tests.masksandspencer.com.diferdin.tests.marksandspencer.domain.Product;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by LONADF on 02/06/2016.
 */

public class CatalogsTest {

    @Test
    public void shouldGetForProduct() {
        Catalog catalog = buildCatalog();

        Product jeans = new Product("Socks", "S01", 7.95);

        Catalog catalog1 = Catalogs.getForProduct(jeans);
        assertNotNull(catalog1);
        assertEquals(catalog.size(), 3);
        assertTrue(catalog.contains(jeans));
    }

    @Test
    public void shouldNotGetForProduct() {
        buildCatalog();

        Product panties = new Product("Panties", "S01", 7.95);
        Catalog catalog1 = Catalogs.getForProduct(panties);
        assertNull(catalog1);
    }

    private Catalog buildCatalog() {
        List<Product> products = new LinkedList<>();

        products.add(new Product("Jeans", "J01", 32.95));
        products.add(new Product("Blouse", "B01", 24.95));
        products.add(new Product("Socks", "S01", 7.95));

        return new Catalog(products);
    }
}
