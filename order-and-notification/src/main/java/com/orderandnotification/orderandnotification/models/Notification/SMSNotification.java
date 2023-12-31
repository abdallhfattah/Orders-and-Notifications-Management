package com.orderandnotification.orderandnotification.models.Notification;

import com.orderandnotification.orderandnotification.models.Customer.Customer;
import com.orderandnotification.orderandnotification.models.Order.SimpleOrder;

public class SMSNotification extends Notification {

    public String send(Customer customer, SimpleOrder simpleorder, String body) {

        String NotificationBody = "Dear " + customer.getUsername() + " your order: "
                + simpleorder.getCart()
                + " is " + body + " . thanks for using our store :)";

        customer.addNotification(NotificationBody);
        // body -> delete
        // confirm
        // ship
        return "Notification been sent";

    }

}
