package com.diferdin.masksandspencer;

import com.diferdin.masksandspencer.Product;
import com.diferdin.masksandspencer.ShoppingList;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by LONADF on 07/06/2016.
 */
public class ShoppingListTest {

    ShoppingList list;

    @Before
    public void configureTest() {
        list = new ShoppingList();
    }

    @Test
    public void shouldIncreaseMultiplicity() {
        Product jeans = new Product("Jeans", "J01", 32.95);
        list.add(jeans);
        list.add(jeans);

        assertEquals(list.size(), 1);
        assertEquals(list.getMultiplicity(jeans.getCode()), 2);
    }

    @Test
    public void shouldDecreaseMultiplicity() {
        Product blouse = new Product("Blouse", "B01", 24.95);

        list.add(blouse);
        list.add(blouse);

        list.remove(blouse);

        assertEquals(list.size(), 1);
        assertEquals(list.getMultiplicity(blouse.getCode()), 1);
        assertTrue(list.contains(blouse));
    }

    @Test
    public void shouldAddProduct() {

        assertTrue(list.isEmpty());

        Product socks = new Product("Socks", "S01", 7.95);
        list.add(socks);

        assertFalse(list.isEmpty());
        assertEquals(list.size(), 1);
        assertEquals(list.getMultiplicity("S01"), 1);
    }

    @Test
    public void shouldRemoveProduct() {
        Product blouse = new Product("Blouse", "B01", 24.95);
        Product jeans = new Product("Jeans", "J01", 32.95);

        list.add(jeans);
        list.add(jeans);
        list.add(blouse);

        list.remove(blouse);

        assertEquals(list.size(), 1);
        assertEquals(list.getMultiplicity(blouse.getCode()), 0);
        assertFalse(list.contains(blouse));
        assertEquals(list.getMultiplicity(jeans.getCode()), 2);
    }
}
