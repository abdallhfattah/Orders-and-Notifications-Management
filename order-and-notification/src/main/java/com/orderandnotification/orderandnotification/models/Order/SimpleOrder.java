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

    public void setCart(Map<Product, Integer> cart) {
        this.cart = cart;
    }

    public SimpleOrder(SimpleOrder simpleOrder) {
        // ObjectMapper objectMapper = new ObjectMapper();
        // String json = objectMapper.writeValueAsString(simpleOrder);
        // this = objectMapper.readValue(json, SimpleOrder.class);
        

    }

    public Map<String, Integer> getCustomerCart() {
        return customerCart;
    }

    public void setCustomerCart(Map<String, Integer> customerCart) {
        this.customerCart = customerCart;
    }

    public SimpleOrder() {
        if (cart == null) {
            this.cart = new HashMap<>();
            this.customerCart = new HashMap<>();
        }
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

        if (cart.containsKey(product)) {
            // add in existing
            int newCount = cart.get(product) + count;

            cart.put(product, newCount);

            customerCart.put(product.getName(), newCount);
        } else {
            // if the product is not in the cart add new entry set for it
            customerCart.put(product.getName(), count);
            cart.put(product, count);
        }
    }

    public void addProducts(Map<Product, Integer> customerOrder) {
        for (Map.Entry<Product, Integer> producEntry : customerOrder.entrySet()) {
            this.addProduct(producEntry.getKey(), producEntry.getValue());
        }
    }

    public void wipeCart() {
        this.cart = new HashMap<>();
        this.customerCart = new HashMap<>();
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
