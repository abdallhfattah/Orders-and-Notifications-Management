package com.orderandnotification.orderandnotification.models.Order.logic;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.orderandnotification.orderandnotification.models.Customer.Customer;
import com.orderandnotification.orderandnotification.models.Order.SimpleOrder;
import com.orderandnotification.orderandnotification.models.prodcut.Product;
import com.orderandnotification.orderandnotification.models.prodcut.logic.ProductRepositorybsl;

@Service
public class SimpleOrderbsl {
	private SimpleOrder simpleOrder;

	public SimpleOrderbsl(Customer customer) {
		simpleOrder = new SimpleOrder();
		if (simpleOrder == null) {
			simpleOrder = new SimpleOrder();
			System.out.println("=======================================================\n");
		}
	}

	public String process(SimpleOrder simpleOrder, ProductRepositorybsl ProductsRepositorybsl,
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

}
