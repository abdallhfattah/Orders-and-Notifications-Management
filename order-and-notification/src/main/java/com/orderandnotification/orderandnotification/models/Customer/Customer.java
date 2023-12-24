package com.orderandnotification.orderandnotification.models.Customer;

import java.util.List;

import org.springframework.stereotype.Component;

import com.orderandnotification.orderandnotification.models.Order.Order;
import com.orderandnotification.orderandnotification.models.Order.SimpleOrder;

@Component
public class Customer {
	// Account account;
	String username;
	String password;
	List<Order> orders;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void makeOrder(SimpleOrder simpleOrder) {
		orders.add(simpleOrder);
	}
}
