package com.orderandnotification.orderandnotification.models.prodcut;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
@Repository
public class ProductsRepository {
	private static ProductsRepository repository;
	private Map<Product , Integer> products;

	
	public ProductsRepository() {
		products = new HashMap<>();
	}

	public ProductsRepository getInstance() {

		if (repository == null) {
			repository = new ProductsRepository();
		}

		return repository;
	}

	public Map<Product , Integer> getAvalibeProduct() {
		return this.products;
	}

	public void addProdcut(Product product) {
		products.put(product, products.getOrDefault(product, 0) + 1);
	}
}
