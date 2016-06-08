package com.diferdin.basket;

/**
 * Created by antonio on 01/06/2016.
 */

public class Product {
    private final String name;
    private final String code;
    private double price;

    public Product(String name, String code, double price) {
        this.name = name;
        this.code = code;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public void setPrice(double newPrice) {
        if(price > 0.0) {
            price = newPrice;
        }
    }

    public double getPrice() {
        return price;
    }

    public Product getByName(String name) {

        return name.equals(this.name) ? this : null;

    }

    @Override
    public String toString() {

        String result =  "Name: "+name+", Code: "+code+", Price: "+price+" ";
        System.out.println(result);
        return result;
    }

    @Override
    public boolean equals(Object object) {
        if(object == null) {
            return false;
        }

        if(!Product.class.isAssignableFrom(object.getClass())) {
            return false;
        }

        final Product product = (Product)object;

        return name.equals(product.getName()) &&
                code.equals(product.getCode()) &&
                price == product.getPrice();
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }
}
