package com.orderandnotification.orderandnotification.models.Customer.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.orderandnotification.orderandnotification.models.Customer.Customer;
import com.orderandnotification.orderandnotification.models.Order.Order;
import com.orderandnotification.orderandnotification.models.Order.SimpleOrder;
import com.orderandnotification.orderandnotification.models.Order.logic.SimpleOrderbsl;
import com.orderandnotification.orderandnotification.models.prodcut.Product;
import com.orderandnotification.orderandnotification.models.prodcut.logic.ProductRepositorybsl;

@Service
public class Customerbsl {

	private Customer customer;
	private CustomerRepositorybsl customersRepository;

	private ProductRepositorybsl ProductsRepositorybsl;
	private SimpleOrderbsl simpleOrderbsl;

	private Map<Product, Integer> customerOrder;

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

		this.customer = customersRepository.getCustomer(name);

		if (customer == null) {
			return "there is no such a user";
		}

		this.customerOrder = new HashMap<>();

		simpleOrderbsl = new SimpleOrderbsl(customer);

		if(customer.getCurrentOrder() == null){
			customer.setCurrentOrder(new SimpleOrder());
		}


		String erros = simpleOrderbsl.simpleOrderprocess(customer.getCurrentOrder(), ProductsRepositorybsl, customerOrder, products,
				customer);

		if (!erros.equals("order added successfully")) {
			return erros;
		}

		this.customer.setCurrentOrder(customer.getCurrentOrder());

		return "order on cart";
	}

	public String placeOrder(String location) {

		if (customerOrder != null && customer.getCurrentOrder() != null) {
			double TotalCost = ProductsRepositorybsl.GetOrderCost(customer.getCurrentOrder());

			if (customer.getBalance() >= TotalCost && customerOrder != null) {

				SimpleOrder simpleOrderCopy = simpleOrderbsl.OrderPlacing(ProductsRepositorybsl, customer, location,
						customerOrder, customer.getCurrentOrder());
				// deductBalance
				this.customer.deductBalance(TotalCost);

				// make the order
				this.customer.makeOrder(simpleOrderCopy);
				customer.setCurrentOrder(null);
				return "Order added Successfully";
			} else {
				customer.setCurrentOrder(null);
				return "Insufficient balance";
			}
		}
		return "add products to your cart";
	}

	public String shipOrder() {

		return simpleOrderbsl.orderShipping(customer, customer.getCurrentOrder());
	}

	public String addBalance(double balance, String name) {
		this.customer = customersRepository.getCustomer(name);
		customer.addBalance(balance);
		return "Balance added!";

	}

	public void placeCurrentOrder(Customer customer){

		this.customerOrder = customer.getCurrentOrder().getCart();

		placeOrder(customer.getLocation());
	}

	public String placeCompoundOrder(List<String> customerNames, String currentCustomer) {

		// current customer
		this.customer = customersRepository.getCustomer(currentCustomer);
		// customer -> location
		//   		-> current order

		placeCurrentOrder(this.customer);

		// customers
		for (String customerName : customerNames) {
			this.customer = customersRepository.getCustomer(customerName);
			placeCurrentOrder(customer);
		}

		return null;
	}
}
