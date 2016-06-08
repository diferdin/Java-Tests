package com.diferdin.masksandspencer;

import com.diferdin.masksandspencer.exception.ShoppingException;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by antonio on 01/06/2016.
 */
public class Basket {
    private Catalog productCatalog;
    private Charge extraCharges;
    private List<Offer> offers;

    private ShoppingList shoppingList;

    public Basket(ShoppingList shoppingList, Catalog productCatalog, List<Offer> offers, Charge extraCharges) {
        if(shoppingList == null) {
            this.shoppingList = new ShoppingList();
        }

        if(productCatalog == null) {
            throw new ShoppingException("Catalog cannot be null");
        }

        this.productCatalog = productCatalog;
        this.offers = offers;
        this.extraCharges = extraCharges;
        this.shoppingList = shoppingList;

        if(!productsAreInCatalog()) {
            throw new ShoppingException("Some products in the shopping list are not in the catalog.");
        }

    }

    private boolean productsAreInCatalog() {
        boolean result = true;
        Set<Product> productCodes = shoppingList.getProducts();

        for(Product product : productCodes) {
            result = result && productCatalog.contains(product.getCode());
        }

        return result;
    }

    public boolean add(String productCode) {
        Optional<Product> product = productCatalog.getProduct(productCode);

        if(product.isPresent()) {
            shoppingList.add(product.get());
            return true;
        }
        return false;
    }

    public boolean remove(String productCode) {

        Optional<Product> product = productCatalog.getProduct(productCode);

        if(product.isPresent()) {
            return shoppingList.remove(product.get());
        }

        return false;
    }

    public double total() {

        if(shoppingList.isEmpty()) {
            return 0;
        }

        double finalTotal = calculateRawTotal() - calculateDiscountsForOffers();

        return Utils.formatNumber(finalTotal + calculateDeliveryCharges());
    }

    private double calculateDiscountsForOffers() {
        double discount = 0d;

        return offers.stream().mapToDouble(o -> o.applyToList(shoppingList)).sum();
    }

    private double calculateDeliveryCharges() {
        return extraCharges.applyToAmount(calculateRawTotal());
    }

    private double calculateRawTotal() {

        double total = 0.0;

        Set<Product> shoppingListProducts = shoppingList.getProducts();
        List<String> productCodes = shoppingListProducts.stream().map(p -> p.getCode()).collect(Collectors.toList());

        return productCodes.stream().mapToDouble(p -> productCatalog.getPriceByCode(p) * shoppingList.getMultiplicity(p)).sum();
    }
}
