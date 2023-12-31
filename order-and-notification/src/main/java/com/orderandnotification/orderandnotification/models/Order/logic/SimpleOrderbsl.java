package com.orderandnotification.orderandnotification.models.Order.logic;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.orderandnotification.orderandnotification.models.Customer.Customer;
import com.orderandnotification.orderandnotification.models.Order.SimpleOrder;
import com.orderandnotification.orderandnotification.models.prodcut.Product;
import com.orderandnotification.orderandnotification.models.prodcut.ProductsRepository;
import com.orderandnotification.orderandnotification.models.prodcut.logic.ProductRepositorybsl;

@Service
public class SimpleOrderbsl {
	private SimpleOrder simpleOrder;

	public SimpleOrderbsl(Customer customer) {
		simpleOrder = new SimpleOrder();
		if (simpleOrder == null) {
			simpleOrder = new SimpleOrder();
		}
	}

	public String simpleOrderprocess(SimpleOrder simpleOrder, ProductRepositorybsl ProductsRepositorybsl,
			Map<Product, Integer> customerOrder,
			Map<String, Integer> products,
			Customer customer) {

		String erros = ProductsRepositorybsl.verifyOrders(customerOrder, products);

		if (!erros.equals("successfully done")) {
			return erros;
		}
		this.simpleOrder.setCustomer(customer);

		simpleOrder.addProducts(customerOrder);
		return "order added successfully";
	}

	public SimpleOrder OrderPlacing(ProductRepositorybsl productRepositorybsl, Customer customer, String location,
			Map<Product, Integer> customerOrder, SimpleOrder simpleOrder) {

		// removing products
		productRepositorybsl.removeProducts(customerOrder);

		// setting a customer for this specific order
		simpleOrder.setCustomer(customer);

		// setting location to the simple order
		simpleOrder.setLocation(location);

		SimpleOrder simpleOrderCopy = new SimpleOrder();
		simpleOrderCopy.copy(simpleOrderCopy, simpleOrder);

		return simpleOrderCopy;
	}

	public String orderShipping(Customer customer, SimpleOrder simpleOrder) {

		if (simpleOrder != null) {
			customer.applyShippingFee(simpleOrder);
			// wiping the cart for shipment
			simpleOrder.wipeCart();
			return "order is being shiped";
		}
		return "there is not order";
	}

	public String CancelOrder(Customer customer, ProductsRepository productsRepository) {

		if (!customer.getOrders().isEmpty()) {

			this.simpleOrder = (SimpleOrder) customer.getOrders().get(customer.getOrders().size() - 1);
			double totalCost = 0.0;
			for (Map.Entry<Product, Integer> product : simpleOrder.getCart().entrySet()) {
				totalCost += (product.getKey().getPrice() * product.getValue());
				// return prodcuts that have been taken
				productsRepository.returnProduct(product.getKey(), product.getValue());
			}

			// add balance
			customer.addBalance(totalCost);
			customer.getOrders().remove(customer.getOrders().size() - 1);
			simpleOrder.wipeCart();
			// customer.getCurrentOrder().wipeCart();
			return "Order Cancelled Successfully";
		}

		return "You have no orders to cancel";
	}

}
