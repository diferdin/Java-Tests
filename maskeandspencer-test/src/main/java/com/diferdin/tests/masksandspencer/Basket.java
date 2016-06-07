package com.diferdin.tests.masksandspencer;

import com.diferdin.tests.masksandspencer.exception.ShoppingException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * Created by antonio on 01/06/2016.
 */
public class Basket {
    private Catalog productCatalog;
    private Charge extraCharges;
    private List<Offer> offers;

    private Map<String, Integer> shoppingList;
    private double total;

    public Basket(Map<String, Integer> shoppingList, Catalog productCatalog, List<Offer> offers, Charge extraCharges) {
        if(shoppingList == null) {
            throw new ShoppingException("Shopping list cannot be null");
        }

        if(productCatalog == null) {
            throw new ShoppingException("Catalog cannot be null");
        }

        if(shoppingList.size() == 0) {
            this.shoppingList = Collections.emptyMap();
            return;
        }

        this.productCatalog = productCatalog;
        this.offers = offers;
        this.extraCharges = extraCharges;
        this.shoppingList = shoppingList;
        total = calculateListTotal();

        if(!productsAreInCatalog()) {
            throw new ShoppingException("Some products in the shopping list are not in the catalog.");
        }

    }

    private boolean productsAreInCatalog() {
        boolean result = true;
        Set<String> productCodes = shoppingList.keySet();

        for(String code : productCodes) {
            result = result && productCatalog.contains(code);
        }
    }

    public boolean add(Product product) {

        if(shoppingList.put(product.getCode())) {
            total += product.getPrice();
            return true;
        }

        return false;
    }

    public double total() {

        if(shoppingList.isEmpty()) {
            return 0;
        }

        total-= calculateDiscountsForOffers();
        double deliveryCharges = calculateDeliveryCharges();

        return round(total + deliveryCharges);
    }

    private double calculateDiscountsForOffers() {
        double discount = 0d;
        for(Offer offer : offers) {
            discount += offer.applyToList(shoppingList);
        }

        return discount;
    }

    private double calculateDeliveryCharges() {
        return extraCharges.applyToAmount(total);
    }

    private double calculateListTotal() {

        double total = 0.0;

        Set<String> productCodes = shoppingList.keySet();

        for(String productCode : productCodes) {
            total += (productCatalog.getPriceByCode(productCode) * shoppingList.get(productCode));
        }

        return total;
    }

    private double round(double value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(2, RoundingMode.DOWN);
        return bd.doubleValue();
    }

    public Catalog getProductCatalog() {
        return productCatalog;
    }

    public List<Product> getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(List<Product> shoppingList) {
        this.shoppingList = shoppingList;
    }
}
