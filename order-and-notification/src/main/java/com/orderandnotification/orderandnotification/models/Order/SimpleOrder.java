package com.orderandnotification.orderandnotification.models.Order;

import java.util.ArrayList;
import java.util.List;

import com.orderandnotification.orderandnotification.models.Customer.Customer;
import com.orderandnotification.orderandnotification.models.prodcut.Product;
import org.springframework.stereotype.Component;

@Component
public class SimpleOrder extends Order {
    private Customer customer;
    private double shippingFee;
    private List<Product> cart;
    private String location;

//    public SimpleOrder(Customer customer) {
//        // super(shippingStrategy);
//        this.customer = customer;
//        this.shippingFee = 20;
//        this.cart = new ArrayList<>();
//        // this.location = location;
//    }

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
    //     this.shippingFee = shippingFee;
    // }

    public List<Product> getCart() {
        return cart;
    }

    public void setCart(List<Product> cart) {
        this.cart = cart;
    }

    public String getLocation() {
        return location;
    }

    public void addProduct(Product prodcut) {
        cart.add(prodcut);
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
