package com.diferdin.tests.masksandspencer.com.diferdin.tests.marksandspencer.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by antonio on 01/06/2016.
 */
public class BuyOneGetOneHalfPrice extends Offer {

    public BuyOneGetOneHalfPrice(String subject, String name) {
        super(subject, name);
    }

    @Override
    public double applyToList(List<Product> shoppingList) {
        double discount = 0.0;

        List<Product> productsEligibleForOffer = shoppingList.stream().filter(p -> offerSubject.equals(p.getName()))
                .collect(Collectors.toList());

        int itemsToDiscount = productsEligibleForOffer.size()/2;

        for(int i = 0; i < itemsToDiscount; i++) {
            Product productOnDiscount = productsEligibleForOffer.get(i);
            discount += productOnDiscount.getPrice()/2d;
        }

        return round(discount);
    }

    private double round(double value) {
        //return (double) Math.round(value * 99) / 99;
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(2, RoundingMode.DOWN);
        return bd.doubleValue();
    }
}
