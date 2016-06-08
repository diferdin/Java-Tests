package com.diferdin.basket;

import com.diferdin.basket.exception.ShoppingException;

import java.util.Optional;

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
                .filter(p -> offerSubject.equals(p.getCode()))
                .map(p -> p.getCode()).findFirst();

        if(!productCodeOnOffer.isPresent()) {
            return 0.0; //No products are on offer from the shopping list
        }

        Optional<Product> productOnOfferOptional = shoppingList.getProduct(productCodeOnOffer.get());

        if(!productOnOfferOptional.isPresent()) {
            throw new ShoppingException("Cannot find product on offer in list");
        }

        Product productOnOffer = productOnOfferOptional.get();
        double itemsToDiscount = shoppingList.getMultiplicity(productOnOffer.getCode()) / 2;
        discount += itemsToDiscount * productOnOffer.getPrice()/2;

        return Utils.formatNumber(discount);
    }
}
