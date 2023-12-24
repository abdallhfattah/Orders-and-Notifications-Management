package com.orderandnotification.orderandnotification.models.Order;

import com.orderandnotification.orderandnotification.models.Customer;
import com.orderandnotification.orderandnotification.models.Shippment.IShippingStrategy;

public abstract class Order {
    private IShippingStrategy shippingStrategy;

    public Order(IShippingStrategy shippingStrategy) {
        this.shippingStrategy = shippingStrategy;
    }

    public void shipOrder() {
        shippingStrategy.ship(this);
    };
}
