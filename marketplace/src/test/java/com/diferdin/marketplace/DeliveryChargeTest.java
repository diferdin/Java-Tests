package com.diferdin.marketplace;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by antonio on 01/06/2016.
 */

public class DeliveryChargeTest {

    @Test
    public void sortDeliveryChargeRulesTest() {

        List<DeliveryChargeRule> charges = new ArrayList<>();

        DeliveryChargeRule firstRule = new DeliveryChargeRule(50.01, 90, 2.95);
        DeliveryChargeRule secondRule = new DeliveryChargeRule(90.01, 0);
        DeliveryChargeRule thirdRule = new DeliveryChargeRule(0, 50, 4.95);

        charges.add(firstRule);
        charges.add(secondRule);
        charges.add(thirdRule);

        DeliveryCharge charge = new DeliveryCharge(charges);

        assertEquals(4.95, charge.applyToAmount(25.09), 0);
        assertEquals(2.95, charge.applyToAmount(56), 0);
        assertEquals(0, charge.applyToAmount(92), 0);
    }
}
