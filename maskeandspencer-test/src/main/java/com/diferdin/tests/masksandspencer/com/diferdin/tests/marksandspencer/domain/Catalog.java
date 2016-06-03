package com.diferdin.tests.masksandspencer.com.diferdin.tests.marksandspencer.domain;

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
        Catalogs.add(this);
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

    public Optional<Product> get(String productId) {
        Optional<Product> productByName = productCatalog.stream().filter(p -> productId.equals(p.getName())).findFirst();
        Optional<Product> productByCode = productCatalog.stream().filter(p -> productId.equals(p.getCode())).findFirst();

        if(productByName.isPresent()){
            return productByName;
        }

        if(productByCode.isPresent()) {
            return productByCode;
        }

        return Optional.empty();
    }

    @Override
    public String toString() {
        String result = "";

        Iterator<Product> productIterator = productCatalog.iterator();

        while(productIterator.hasNext()) {
            result = result + productIterator.next().toString();
        }

        return result;
    }

    public boolean contains(Product product) {
        return this.contains(product.getName());
    }

    public boolean contains(String productName) {
        List<Product> matchingProduct = productCatalog
                .stream()
                .filter(p -> productName.equals(p.getName()))
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
