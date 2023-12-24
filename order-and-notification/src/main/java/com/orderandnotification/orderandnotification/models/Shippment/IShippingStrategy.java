package com.orderandnotification.orderandnotification.models.Shippment;

import com.orderandnotification.orderandnotification.models.Order.Order;

public interface IShippingStrategy {
    public void ship(Order order);
}
