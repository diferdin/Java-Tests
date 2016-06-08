package com.diferdin.basket;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by LONADF on 07/06/2016.
 */
public class ShoppingList {
    Map<Product, Integer> map;

    public ShoppingList() {
        map = new HashMap<>();
    }

    public void add(Product product) {
        if(map == null) {
            map = new HashMap<>();
        }

        List<String> productCodes = map.keySet().stream().map(p -> p.getCode()).collect(Collectors.toList());

        if(map.isEmpty() || !productCodes.contains(product.getCode())) {
            map.put(product, 1);
        } else {
            increaseMultiplicity(product);
        }
    }

    private void increaseMultiplicity(Product product) {
        int productMultiplicity = getMultiplicity(product.getCode());
        productMultiplicity++;
        map.replace(product, productMultiplicity);
    }

    public boolean remove(Product product) {
        if(map == null) {
            map = new HashMap<>();
            return false;
        }

        if(map.isEmpty()) {
            return false;
        }

        if(map.containsKey(product)) {
            int multiplicity = map.get(product);
            multiplicity--;

            if(multiplicity == 0) {
                map.remove(product);
            } else {
                map.put(product, multiplicity);
            }
        }
        return true;
    }

    public boolean contains(Product product) {
        return map.containsKey(product);
    }

    public Set<Product> getProducts() {
        return map.keySet();
    }

    public int getMultiplicity(String productCode) {
        Optional<Product> product = getProduct(productCode);
        if(product.isPresent()) {
            return map.get(product.get());
        }
        return 0;
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public int size() {
        return map.size();
    }

    public Optional<Product> getProduct(String productCode) {
        Set<Product> products = map.keySet();

        List<Product> productList = products.stream().filter(p -> productCode.equals(p.getCode())).collect(Collectors.toList());

        if(productList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(productList.get(0));
        }
    }
}