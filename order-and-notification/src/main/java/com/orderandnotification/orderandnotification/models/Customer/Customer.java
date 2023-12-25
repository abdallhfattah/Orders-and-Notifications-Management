package com.orderandnotification.orderandnotification.models.Customer;

import java.util.List;

import org.springframework.stereotype.Component;

import com.orderandnotification.orderandnotification.models.Order.Order;

@Component
public class Customer {
	// Account account;
	String username;
	String password;
	List<Order> orders;// = new ArrayList<>();

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}


	// public Customer(String username, String password) {
	// 	this.username = username;
	// 	this.password = password;
	// }

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

	public void makeOrder(Order order) {
		orders.add(order);
	}
}
