package com.orderandnotification.orderandnotification.models.Order;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.orderandnotification.orderandnotification.models.Customer.Customer;
import com.orderandnotification.orderandnotification.models.prodcut.Product;

@Component
public class SimpleOrder extends Order {
    @JsonIgnore
    private Customer customer;
    private double shippingFee;
    private Map<Product, Integer> cart;
    private String location;

    public SimpleOrder() {

    }

    public SimpleOrder(Customer customer) {
        // super(shippingStrategy);
        this.customer = customer;
        this.shippingFee = 20;
        this.cart = new HashMap<>();
        this.location = null;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setShippingFee(double shippingFee) {
        this.shippingFee = shippingFee;
    }

    public Customer getCustomer() {
        return customer;
    }

    // public void setCustomer(Customer customer) {
    // this.customer = customer;
    // }

    public double getShippingFee() {
        return shippingFee;
    }

    // public void setShippingFee(double shippingFee) {
    // this.shippingFee = shippingFee;
    // }

    public Map<Product, Integer> getCart() {
        return cart;
    }

    // public void setCart(List<Product> cart) {
    // this.cart = cart;
    // }

    public String getLocation() {
        return location;
    }

    public void addProduct(Product product, Integer count) {
        if (cart == null) {
            this.cart = new HashMap<>();
        }

        cart.put(product, count);

    }

    public void setLocation(String location) {
        this.location = location;
    }
}
