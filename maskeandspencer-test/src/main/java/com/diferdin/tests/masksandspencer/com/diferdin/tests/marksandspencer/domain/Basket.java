package com.diferdin.tests.masksandspencer.com.diferdin.tests.marksandspencer.domain;

import com.diferdin.tests.masksandspencer.exception.ShoppingException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by antonio on 01/06/2016.
 */
public class Basket {
    private Catalog productCatalog;
    private Charge deliveryCharge;
    private List<Offer> offers;

    private List<Product> shoppingList;
    private double total;

    public Basket(List<Product> shoppingList) {
        if(shoppingList == null) {
            throw new ShoppingException("Shopping list cannot be null");
        }

        if(shoppingList.size() == 0) {
            this.shoppingList = Collections.emptyList();
            return;
        }

        productCatalog = Catalogs.getForProduct(shoppingList.get(0));

        if(productCatalog == null) {
            throw new ShoppingException("No catalog found");
        }
        offers = Offers.getForCatalog(productCatalog);

        if(productCatalog == null) {
            throw new ShoppingException("Product could not be found on any catalog");
        }

        this.shoppingList = shoppingList;
        total = calculateTotal();
        deliveryCharge = Charges.getDeliveryCharge();
    }

    public Basket() {
        shoppingList = new ArrayList<>();
    }

    public boolean add(Product product) {

        if(shoppingList.add(product)) {
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
        return deliveryCharge.applyToAmount(total);
    }

    private double calculateTotal() {

        double total = 0.0;

        Iterator shoppingListIterator = shoppingList.iterator();

        while(shoppingListIterator.hasNext()) {
            Product product = (Product) shoppingListIterator.next();
            total += product.getPrice();
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
