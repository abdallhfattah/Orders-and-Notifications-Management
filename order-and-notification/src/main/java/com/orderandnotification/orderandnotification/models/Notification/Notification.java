package com.orderandnotification.orderandnotification.models.Notification;

import com.orderandnotification.orderandnotification.models.Customer.Customer;
import com.orderandnotification.orderandnotification.models.Order.Order;
import com.orderandnotification.orderandnotification.models.Order.SimpleOrder;

public abstract class Notification {
    public void NotificationTemplate(Customer customer , SimpleOrder simpleorder){
        greeting(customer);
        send(customer , simpleorder);
    }
    public abstract String send(Customer customer , SimpleOrder simpleorder);
    public String greeting(Customer customer)
    {
        return "Dear " + customer.getUsername();
    }
}
