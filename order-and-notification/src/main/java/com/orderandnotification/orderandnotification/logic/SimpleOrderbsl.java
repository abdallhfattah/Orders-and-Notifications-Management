package com.orderandnotification.orderandnotification.logic;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.orderandnotification.orderandnotification.models.Customer;
import com.orderandnotification.orderandnotification.models.Product;
import com.orderandnotification.orderandnotification.models.Order.SimpleOrder;

@Service
public class SimpleOrderbsl {
	private SimpleOrder simpleOrder;

	SimpleOrderbsl(Customer customer) {
		simpleOrder = new SimpleOrder(customer);
	}

	@PostMapping("/addProdcut")
	public void addProdcut(Product prodcut) {
		simpleOrder.addProduct(prodcut);
	}

	@GetMapping("/getProdcuts")
	public List<Product> seeCart() {
		return simpleOrder.getCart();
	}

	@PostMapping("/setlocation/{location}")
	public void setLocation(@PathVariable("location") String location){
		simpleOrder.setLocation(location);
	}
}
