package com.orderandnotification.orderandnotification.models.Customer;

import java.util.List;

import org.springframework.stereotype.Component;

import com.orderandnotification.orderandnotification.models.Order.Order;
import com.orderandnotification.orderandnotification.models.Order.SimpleOrder;

@Component
public class Customer {
	String username;
	String password;
	double balance = 0.0;
	List<Order> orders;
	String location;
	SimpleOrder currentOrder;
	String Email;
	String PhoneNumber;

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public SimpleOrder getCurrentOrder() {
		return currentOrder;
	}

	public void setCurrentOrder(SimpleOrder currentOrder) {
		this.currentOrder = currentOrder;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void addBalance(double balance) {
		this.balance += balance;
	}

	public void deductBalance(double balance) {
		this.balance -= balance;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

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
		currentOrder = null;
	}

	public void applyShippingFee(SimpleOrder order) {
		balance -= order.getShippingFee();
	}
}
