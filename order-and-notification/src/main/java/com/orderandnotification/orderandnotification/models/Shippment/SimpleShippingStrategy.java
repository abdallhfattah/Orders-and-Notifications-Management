package com.orderandnotification.orderandnotification.models.Shippment;

import org.springframework.stereotype.Component;

import com.orderandnotification.orderandnotification.models.Order.Order;

@Component
public class SimpleShippingStrategy implements IShippingStrategy {
    @Override
    public void ship(Order order) {
        // map<order , customer>
    }
}
