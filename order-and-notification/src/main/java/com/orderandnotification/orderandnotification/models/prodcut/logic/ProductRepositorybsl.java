package com.orderandnotification.orderandnotification.models.prodcut.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.orderandnotification.orderandnotification.models.Order.SimpleOrder;
import com.orderandnotification.orderandnotification.models.prodcut.Product;
import com.orderandnotification.orderandnotification.models.prodcut.ProductsRepository;

@Service
public class ProductRepositorybsl {
	private final ProductsRepository repository;

	public ProductsRepository getRepository() {
		return repository;
	}

	public ProductRepositorybsl() {
		repository = new ProductsRepository();
	}

	public String addProduct(Product product) {
		repository.addProduct(product);
		return "Product added";
	}

	public Map<Product, Integer> getProducts() {
		return repository.getAvalibeProduct();
	}

	public Map<String, Integer> getCategoryItems() {
		Map<String, Integer> categories = new HashMap<>();

		for (Map.Entry<Product, Integer> entry : repository.getAvalibeProduct().entrySet()) {
			categories.put(entry.getKey().getCategory(),
					categories.getOrDefault(entry.getKey().getCategory(), 0) + entry.getValue());
		}

		return categories;
	}

	public List<Object> returnProducts() {
		List<Object> products = new ArrayList<>();
		for (Map.Entry<Product, Integer> product : getProducts().entrySet()) {
			products.add(product.getKey());
		}
		products.add(getCategoryItems());
		return products;
	}

	public void removeProducts(Map<Product, Integer> prodcutsToRemove) {
		// updating the repository
		for (Map.Entry<Product, Integer> product : prodcutsToRemove.entrySet()) {
			repository.removeProduct(product.getKey(), product.getValue());
		}
	}

	public String verifyOrders(Map<Product, Integer> customerOrder, Map<String, Integer> products) {
		for (Map.Entry<String, Integer> product : products.entrySet()) {

			boolean productFound = false;

			for (Map.Entry<Product, Integer> entry : getProducts().entrySet()) {

				if (product.getKey() == entry.getKey().getName()) {
					productFound = true;
					// insufficient category number
					if (entry.getValue() < product.getValue()) {
						return "We have only " + entry.getValue() + " from this product " + product.getKey();
					}
					// totalCost += (product.getValue() * entry.getKey().getPrice());
					// add
					customerOrder.put(entry.getKey(), product.getValue());
				}
			}

			if (!productFound) {
				return "This " + product.getKey() + " was not found , Order is cancelled";
			}
		}
		return "successfully done";
	}

	public double GetOrderCost(SimpleOrder simpleOrder) {
		double TotalCost = 0.0;
		
		for (Map.Entry<Product, Integer> product : simpleOrder.getCart().entrySet()) {
			TotalCost += (product.getValue() * product.getKey().getPrice());
		}

		return TotalCost;
	}

	public Product getProduct(int serial) {
		return repository.getProductBySerial(serial);
	}
}
