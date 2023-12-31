package com.orderandnotification.orderandnotification.models.Customer.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.orderandnotification.orderandnotification.models.Customer.Customer;
import com.orderandnotification.orderandnotification.models.Notification.logic.Notificationbsl;
import com.orderandnotification.orderandnotification.models.Order.Order;
import com.orderandnotification.orderandnotification.models.Order.SimpleOrder;
import com.orderandnotification.orderandnotification.models.Order.logic.CompoundOrderbsl;
import com.orderandnotification.orderandnotification.models.Order.logic.SimpleOrderbsl;
import com.orderandnotification.orderandnotification.models.prodcut.Product;
import com.orderandnotification.orderandnotification.models.prodcut.logic.ProductRepositorybsl;

@Service
public class Customerbsl {

	private Customer customer;

	private CustomerRepositorybsl customersRepository;
	private ProductRepositorybsl productRepositorybsl;
	private SimpleOrderbsl simpleOrderbsl;
	private Notificationbsl notificationbsl;
	private Map<Product, Integer> customerOrder;

	public void setSimpleOrderbsl() {
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Map<Product, Integer> getCustomerOrder() {
		return customerOrder;
	}

	public void setCustomerOrder(Map<Product, Integer> customerOrder) {
		this.customerOrder = customerOrder;
	}

	public Customerbsl(Notificationbsl notificationbsl, CustomerRepositorybsl cbsl,
			ProductRepositorybsl productRepositorybsl) {
		this.customersRepository = cbsl;
		this.productRepositorybsl = productRepositorybsl;
		this.notificationbsl = notificationbsl;
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

		if (customer.getCurrentOrder() == null) {
			customer.setCurrentOrder(new SimpleOrder());
		}

		String erros = simpleOrderbsl.simpleOrderprocess(customer.getCurrentOrder(), productRepositorybsl,
				customerOrder, products,
				customer);

		if (!erros.equals("order added successfully")) {
			return erros;
		}

		this.customer.setCurrentOrder(customer.getCurrentOrder());

		return "order on cart";
	}

	public String placeOrder(String location) {

		if (customerOrder != null && customer.getCurrentOrder() != null) {
			double TotalCost = productRepositorybsl.GetOrderCost(customer.getCurrentOrder());

			if (customer.getBalance() >= TotalCost && customerOrder != null) {

				SimpleOrder simpleOrderCopy = simpleOrderbsl.OrderPlacing(productRepositorybsl, customer, location,
						customerOrder, customer.getCurrentOrder());

				// deductBalance
				this.customer.deductBalance(TotalCost);
				// make the order
				this.customer.makeOrder(simpleOrderCopy);

				notificationbsl.placeOrderNotification(customer);

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

		int orders = customer.getOrders().size();
		if (orders != 0) {
			SimpleOrder simpleOrder = (SimpleOrder) customer.getOrders().get(orders - 1);
			if (!simpleOrder.isShiped()) {

				SimpleOrder simpleOrderCopy = new SimpleOrder();

				notificationbsl.shipOrderNotification(customer);
				simpleOrder.setShiped(true);
				simpleOrderCopy.copy(simpleOrderCopy,
						(SimpleOrder) customer.getOrders().get(customer.getOrders().size() - 1));
				return simpleOrderbsl.orderShipping(customer, simpleOrderCopy);
			}
			return "all orders are shipped";
		}

		return "there is no order to ship";
	}

	public String addBalance(double balance, String name) {
		this.customer = customersRepository.getCustomer(name);
		customer.addBalance(balance);
		return "Balance added!";
	}

	public void placeCurrentOrder(Customer customer, int customersInCompoundOrder, SimpleOrderbsl simpleOrderbsl) {

		this.customerOrder = customer.getCurrentOrder().getCart();

		customer.getCurrentOrder().setLocation(customer.getLocation());
		customer.getCurrentOrder()
				.setShippingFee(customer.getCurrentOrder().getShippingFee() / customersInCompoundOrder);

		this.simpleOrderbsl = simpleOrderbsl;

		placeOrder(customer.getLocation());
	}

	public String placeCompoundOrder(List<String> customerNames, String currentCustomer) {

		CompoundOrderbsl compoundOrderbsl = new CompoundOrderbsl(
				new Customerbsl(notificationbsl, customersRepository, productRepositorybsl));

		customerNames.add(currentCustomer);

		return compoundOrderbsl.placeCompoundOrder(customersRepository, customerNames);

	}

	public String cancelOrder() {
		if (!customer.getOrders().isEmpty()) {
			notificationbsl.deleteOrderNotification(customer);
			return simpleOrderbsl.CancelOrder(customer, productRepositorybsl.getRepository());
		}
		return "You have no orders to cancel";
	}
}
