package com.orderandnotification.orderandnotification.models.Notification;

import com.orderandnotification.orderandnotification.models.Customer.Customer;
import com.orderandnotification.orderandnotification.models.Order.SimpleOrder;

public abstract class Notification {

    
    public void NotificationTemplate(Customer customer, SimpleOrder simpleorder, String body) {
        send(customer, simpleorder, body);
    }

    public abstract String send(Customer customer, SimpleOrder simpleorder, String body);

}
