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
    private double shippingFee = 20;
    @JsonIgnore
    private Map<Product, Integer> cart;
    private Map<String, Integer> customerCart;
    private String location;

    public Map<String, Integer> getCustomerCart() {
        return customerCart;
    }

    public void setCustomerCart(Map<String, Integer> customerCart) {
        this.customerCart = customerCart;
    }
    public SimpleOrder() {}
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setShippingFee(double shippingFee) {
        this.shippingFee = shippingFee;
    }

    public Customer getCustomer() {return customer;}

    public double getShippingFee() {
        return shippingFee;
    }

    public Map<Product, Integer> getCart() {
        return cart;
    }

    public String getLocation() {
        return location;
    }

    public void addProduct(Product product, Integer count) {
        if (cart == null) {
            this.cart = new HashMap<>();
            this.customerCart = new HashMap<>();
        }
        customerCart.put(product.getName(), count);
        cart.put(product, count);
    }
    public void setLocation(String location) {
        this.location = location;
    }
}
