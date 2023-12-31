package com.orderandnotification.orderandnotification.models.Notification;

import com.orderandnotification.orderandnotification.models.Customer.Customer;
import com.orderandnotification.orderandnotification.models.Order.SimpleOrder;

public class SMSNotification extends Notification {
    public String send(Customer customer , SimpleOrder simpleorder)
    {
        return "your booking of the " + simpleorder.getCart()+ " is confirmed. thanks for using our store :)";
    }
}
