package com.diferdin.tests.masksandspencer;

import com.diferdin.tests.masksandspencer.exception.ShoppingException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by antonio on 01/06/2016.
 */
public class BuyOneGetOneHalfPrice extends Offer {

    public BuyOneGetOneHalfPrice(String subject, String name) {
        super(subject, name);
    }

    @Override
    public double applyToList(ShoppingList shoppingList) {
        double discount = 0.0;

        Optional<String> productCodeOnOffer = shoppingList.getProducts()
                .stream()
                .filter(p -> offerSubject.equals(p))
                .map(p -> p.getCode()).findFirst();

        Optional<Product> productOnOfferOptional = shoppingList.getProduct(productCodeOnOffer.get());

        if(!productOnOfferOptional.isPresent()) {
            throw new ShoppingException("Cannot find product on offer in list");
        }

        Product productOnOffer = productOnOfferOptional.get();
        double itemsToDiscount = shoppingList.getMultiplicity(productOnOffer.getCode()) / 2;
        discount += productOnOffer.getPrice() * itemsToDiscount;

        return round(discount);
    }

    private double round(double value) {
        //return (double) Math.round(value * 99) / 99;
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(2, RoundingMode.DOWN);
        return bd.doubleValue();
    }
}
