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

		String erros = simpleOrderbsl.process(simpleOrder, ProductsRepositorybsl, customerOrder, products, customer);

		if (!erros.equals("order added successfully")) {
			return erros;
		}

		this.customer.setCurrentOrder(simpleOrder);

		return "order on cart";
	}

	public String placeOrder(String location) {

		if (customerOrder != null) {
			double TotalCost = ProductsRepositorybsl.GetOrderCost(simpleOrder);

			if (customer.getBalance() >= TotalCost && customerOrder != null) {

				// removing products
				ProductsRepositorybsl.removeProducts(customerOrder);

				// setting a customer for this specific order
				this.simpleOrder.setCustomer(customer);

				// setting location to the simple order
				this.simpleOrder.setLocation(location);

				// deductBalance
				this.customer.deductBalance(TotalCost);

				// make the order
				SimpleOrder copy = new SimpleOrder();
				copy.setCustomer(simpleOrder.getCustomer());
				copy.setCustomerCart(simpleOrder.getCustomerCart());
				copy.setLocation(simpleOrder.getLocation());
				copy.setCart(simpleOrder.getCart());
				copy.setShippingFee(simpleOrder.getShippingFee());
				this.customer.makeOrder(copy);

				return "Order added Successfully";
			} else {
				return "Insufficient balance";
			}
		}
		return "add products to your cart";
	}

	public String shipOrder() {
		if (simpleOrder != null) {

			this.customer.applyShippingFee(simpleOrder);

			// wiping the cart for shipment
			simpleOrder.wipeCart();

			return "order is being shiped";
		}

		return "there is not order";
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
