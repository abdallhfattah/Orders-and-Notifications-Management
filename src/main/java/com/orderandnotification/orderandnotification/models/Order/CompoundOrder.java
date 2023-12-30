package com.orderandnotification.orderandnotification.models.Order;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CompoundOrder extends Order{
    private List<SimpleOrder> simpleOrders;

//    public CompoundOrder(IShippingStrategy shippingStrategy) {
//        // super(shippingStrategy);
//    }
    public void addSimpleOrder(SimpleOrder simpleOrder) {
        simpleOrders.add(simpleOrder);
    }
}
