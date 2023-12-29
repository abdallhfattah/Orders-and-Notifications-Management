package com.orderandnotification.orderandnotification.models.Customer.logic;

import java.util.List;

import org.springframework.stereotype.Service;

import com.orderandnotification.orderandnotification.models.Customer.Customer;
import com.orderandnotification.orderandnotification.models.Order.Order;
import com.orderandnotification.orderandnotification.models.Order.SimpleOrder;

@Service
public class Customerbsl {
	private Customer customer;
	private CustomerRepositorybsl customersRepository;
	private SimpleOrder simpleOrder;

	public Customerbsl(Customer customer, CustomerRepositorybsl cbsl) {
		this.customer = customer;
		customersRepository = cbsl;
	}

	public List<Order> getOrders(String name) {
		customer = customersRepository.getCustomer(name);
		return customer.getOrders();
	}

	public String addSimpleOrder(SimpleOrder simpleOrder, String name) {
		this.simpleOrder = simpleOrder;
		this.customer = customersRepository.getCustomer(name);

		if (customer == null) {
			return "there is no such a user";
		}

		 this.simpleOrder.setCustomer(customer);

		customer.makeOrder(simpleOrder);

		return "Order added Successfully";
	}

	public String addBalance(double balance, String name) {
		this.customer = customersRepository.getCustomer(name);
		customer.addBalance(balance);
		return "Balance added!";
	}

	public String deductBalance(double balance, String name) {
		this.customer = customersRepository.getCustomer(name);
		if (balance > customer.getBalance()) {
			return "Insufficient Balance";
		}
		customer.addBalance(balance);
		return "Amount Deducted and Order is placed!";
	}
}
