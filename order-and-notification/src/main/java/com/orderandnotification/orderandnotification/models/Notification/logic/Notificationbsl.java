package com.orderandnotification.orderandnotification.models.Notification.logic;

import org.springframework.stereotype.Service;

import com.orderandnotification.orderandnotification.models.Customer.Customer;
import com.orderandnotification.orderandnotification.models.Notification.SMSNotification;
import com.orderandnotification.orderandnotification.models.Order.SimpleOrder;

@Service
public class Notificationbsl {
	private SMSNotification notification;

	Notificationbsl() {
		notification = new SMSNotification();
	}

	public void deleteOrderNotification(Customer customer) {
		notification.send(customer, (SimpleOrder) customer.getOrders().get(customer.getOrders().size() - 1), "delete");
	}

	public void placeOrderNotification(Customer customer) {
		notification.send(customer, (SimpleOrder) customer.getOrders().get(customer.getOrders().size() - 1), "confirm");
	}

	public void shipOrderNotification(Customer customer) {
		notification.send(customer, (SimpleOrder) customer.getOrders().get(customer.getOrders().size() - 1), "is beeing shipped");
	}

}
