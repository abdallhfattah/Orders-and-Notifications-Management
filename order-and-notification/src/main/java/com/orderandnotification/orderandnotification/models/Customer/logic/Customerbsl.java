package com.orderandnotification.orderandnotification.models.Customer.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.orderandnotification.orderandnotification.models.Customer.Customer;
import com.orderandnotification.orderandnotification.models.Order.Order;
import com.orderandnotification.orderandnotification.models.Order.SimpleOrder;
import com.orderandnotification.orderandnotification.models.prodcut.Product;
import com.orderandnotification.orderandnotification.models.prodcut.logic.ProductRepositorybsl;

@Service
public class Customerbsl {
	private Customer customer;
	private CustomerRepositorybsl customersRepository;
	private SimpleOrder simpleOrder;
	private ProductRepositorybsl ProductsRepositorybsl;

	public Customerbsl(Customer customer, CustomerRepositorybsl cbsl, ProductRepositorybsl ProductsRepositorybsl) {
		this.customer = customer;
		this.customersRepository = cbsl;
		this.ProductsRepositorybsl = ProductsRepositorybsl;
	}

	public List<Order> getOrders(String name) {
		customer = customersRepository.getCustomer(name);
		return customer.getOrders();
	}

	public String addSimpleOrder(Map<String, Integer> products, String name) {
		this.simpleOrder = new SimpleOrder();

		this.customer = customersRepository.getCustomer(name);

		if (customer == null) {
			return "there is no such a user";
		}

		double TotalCost = 0;

		Map<Product,Integer> customerOrder = new HashMap<>();
		for (Map.Entry<String, Integer> product : products.entrySet()) {

			boolean productFound = false;

			for (Map.Entry<Product, Integer> entry : ProductsRepositorybsl.getProducts().entrySet()) {

				if (product.getKey() == entry.getKey().getName()) {
					productFound = true;
					
					// insufficient category number
					if (entry.getValue() < product.getValue()) {
						return "We have only " + entry.getValue() + " from this product " + product.getKey();
					}
					// add
					customerOrder.put(entry.getKey() , product.getValue());
				}
				
			}

			if(!productFound){
				return "This " + product.getKey()  + " was not found , Order is cancelled";
			}
		}


		if (customer.getBalance() >= TotalCost && customerOrder != null) {
			// removing products
			ProductsRepositorybsl.removeProducts(customerOrder);

			this.simpleOrder.setCustomer(customer);

			customer.deductBalance(TotalCost);
			
			customer.makeOrder(simpleOrder);
			return "Order added Successfully";
		} else {
			return "Insufficient balance";
		}
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
