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

	public Customerbsl(Customer customer, CustomerRepositorybsl cbsl) {
		this.customer = customer;
		customersRepository = cbsl;
	}

	public List<Order> getOrders(String name) {
		customer = customersRepository.getCustomer(name);
		return customer.getOrders();
	}

	public void addSimpleOrder(SimpleOrder simpleOrder , String name){
		customer = customersRepository.getCustomer(name);
		customer.makeOrder(simpleOrder);
		simpleOrder.setCustomer(customer);
	}

}
