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
	private SimpleOrder simpleOrder;
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

		if (simpleOrder == null) {
			this.simpleOrder = new SimpleOrder();
		}

		String erros = simpleOrderbsl.simpleOrderprocess(simpleOrder, ProductsRepositorybsl, customerOrder, products,
				customer);

		if (!erros.equals("order added successfully")) {
			return erros;
		}

		this.customer.setCurrentOrder(simpleOrder);

		return "order on cart";
	}

	public String placeOrder(String location) {

		if (customerOrder != null && simpleOrder != null) {
			double TotalCost = ProductsRepositorybsl.GetOrderCost(simpleOrder);

			if (customer.getBalance() >= TotalCost && customerOrder != null) {

				SimpleOrder simpleOrderCopy = simpleOrderbsl.OrderPlacing(ProductsRepositorybsl, customer, location,
						customerOrder, simpleOrder);
				// deductBalance
				this.customer.deductBalance(TotalCost);

				// make the order
				this.customer.makeOrder(simpleOrderCopy);
				simpleOrder = null;
				return "Order added Successfully";
			} else {
				simpleOrder = null;
				return "Insufficient balance";
			}
		}
		return "add products to your cart";
	}

	public String shipOrder() {

		return simpleOrderbsl.orderShipping(customer, simpleOrder);
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
