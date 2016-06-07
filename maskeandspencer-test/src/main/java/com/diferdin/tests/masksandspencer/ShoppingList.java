package com.diferdin.tests.masksandspencer;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by LONADF on 07/06/2016.
 */
public class ShoppingList {
    Map<Product, Integer> list;

    public boolean add(Product product) {
        if(list == null) {
            list = new HashMap<>();
        }

        if(list.isEmpty() || !list.containsKey(product)) {
            list.put(product, 1);
            return true;
        }

        int productMultiplicity = list.get(product);
        list.put(product, productMultiplicity++);
        return true;
    }

    public boolean remove(Product product) {
        if(list == null) {
            list = new HashMap<>();
            return false;
        }

        if(list.isEmpty()) {
            return false;
        }

        if(list.containsKey(product)) {
            int multiplicity = list.get(product);
            multiplicity--;

            if(multiplicity == 0) {
                list.remove(product);
            } else {
                list.put(product, multiplicity);
            }
        }
        return true;
    }

    public boolean contains(Product product) {
        return list.containsKey(product);
    }

    public Set<Product> getProducts() {
        return list.keySet();
    }

    public int getMultiplicity(String productCode) {
        return list.get(productCode);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    public Optional<Product> getProduct(String productCode) {
        Set<Product> products = list.keySet();

        List<Product> productList = products.stream().filter(p -> productCode.equals(p.getCode())).collect(Collectors.toList());

        if(productList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(productList.get(0));
        }
    }
}