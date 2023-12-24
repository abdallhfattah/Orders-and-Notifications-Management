package com.orderandnotification.orderandnotification.models.Order;

import com.orderandnotification.orderandnotification.models.Customer;
import com.orderandnotification.orderandnotification.models.Prodcut;
import com.orderandnotification.orderandnotification.models.Shippment.IShippingStrategy;

import java.util.ArrayList;
import java.util.List;

public class SimpleOrder extends Order {
    private Customer customer;
    private double shippingFee;
    private List<Prodcut> cart;
    private String location;

    public SimpleOrder(IShippingStrategy shippingStrategy, Customer customer, String location) {
        super(shippingStrategy);
        this.customer = customer;
        this.shippingFee = 20;
        this.cart = new ArrayList<>();
        this.location = location;
    }
    public void addProduct(Prodcut prodcut) {
        cart.add(prodcut);
    }
    public double calculateTotalPrice() {
        double totalPrice = 0;
        for (Prodcut prodcut : cart) {
            totalPrice += prodcut.getPrice();
        }
        totalPrice += shippingFee;
        return totalPrice;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
