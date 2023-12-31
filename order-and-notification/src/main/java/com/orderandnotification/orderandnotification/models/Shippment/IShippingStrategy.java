package com.orderandnotification.orderandnotification.models.Shippment;

import org.springframework.stereotype.Component;

import com.orderandnotification.orderandnotification.models.Order.Order;

@Component
public interface IShippingStrategy {
    public void ship(Order order);
}
