package com.diferdin.marketplace;

import com.diferdin.marketplace.exception.ShoppingException;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by antonio on 01/06/2016.
 */

public class Catalog {

    private Set<Product> productCatalog = null;
    private final CatalogName name = CatalogName.CLOTHING;

    public Catalog() {
        productCatalog = new HashSet<Product>();
    }

    public Catalog(List<Product> products) {
        if(productCatalog == null) {
            productCatalog = new HashSet<Product>();
        }

        productCatalog.addAll(products);
    }

    public Set<Product> getProducts() {

        return productCatalog;

    }

    public CatalogName getName() {
        return name;
    }

    public int size() {
        return productCatalog.size();
    }

    public double getPriceByCode(String productCode) throws ShoppingException {
        Optional<Product> productByCode = productCatalog.stream().filter(p -> productCode.equals(p.getCode())).findFirst();

        if(productByCode.isPresent()) {
            return productByCode.get().getPrice();
        }

        return 0.0;
    }

    public Optional<Product> getProduct(String productCode) {
        Optional<Product> product = productCatalog.stream().filter(p -> productCode.equals(p.getCode())).findFirst();

        if(product.isPresent()) {
            return product;
        }

        return Optional.empty();
    }

    @Override
    public String toString() {
        String result = "";

        return productCatalog.stream().map(c -> c.toString()).collect(Collectors.joining(", "));
    }

    public boolean contains(Product product) {
        return this.contains(product.getName());
    }

    public boolean contains(String productCode) {
        List<Product> matchingProduct = productCatalog
                .stream()
                .filter(p -> productCode.equals(p.getCode()))
                .collect(Collectors.toList());

        return matchingProduct.size() == 1;
    }

    public boolean addProduct(Product product) {
        if(product != null) {
            return productCatalog.add(product);
        }
        return false;
    }
}
