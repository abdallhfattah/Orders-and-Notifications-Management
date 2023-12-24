package com.orderandnotification.orderandnotification.models.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomersRepository {
	List<Customer> customers;

	 public CustomersRepository() {
	 	this.customers = new ArrayList<>();
	 }

	public List<Customer> getCustomers() {
		return customers;
	}

	public void addCustomer(Customer customer){
		customers.add(customer);
	}
}
