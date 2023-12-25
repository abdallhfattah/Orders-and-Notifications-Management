package com.orderandnotification.orderandnotification.models.Shippment;

import com.orderandnotification.orderandnotification.models.Order.Order;
import org.springframework.stereotype.Component;

@Component
public interface IShippingStrategy {
    public void ship(Order order);
}
