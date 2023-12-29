package com.orderandnotification.orderandnotification.models.prodcut;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class ProductsRepository {
	private static ProductsRepository repository;
	private Map<Product, Integer> products;

	public ProductsRepository() {
		products = new HashMap<>();
		Product product1 = new Product(1, "hat", "monster", "clothes", 30);
		Product product2 = new Product(2, "T-shirt", "Nike", "clothes", 50);
		Product product3 = new Product(3, "pants", "LcWikiki", "clothes", 60);
		Product product5 = new Product(4, "jacket", "eagle", "clothes", 150);
		Product product4 = new Product(5, "shoe", "Adidas", "shoe", 70);
		Product product6 = new Product(6, "dress", "eagle", "dress", 70);
		Product product7 = new Product(7, "classes", "celinence", "classes", 100);

		loadingProdcut(product1);
		loadingProdcut(product2);
		loadingProdcut(product3);
		loadingProdcut(product4);
		loadingProdcut(product5);
		loadingProdcut(product6);
		loadingProdcut(product7);

	}

	public void loadingProdcut(Product product) {
		for (int i = 0; i < 20; i++) {
			addProduct(product);
		}
	}

	public ProductsRepository getInstance() {

		if (repository == null) {
			repository = new ProductsRepository();
		}

		return repository;
	}

	public Map<Product, Integer> getAvalibeProduct() {
		return this.products;
	}

	public void addProduct(Product product) {
		products.put(product, products.getOrDefault(product, 0) + 1);
		// System.out.println(products.get(product));
	}

	public void removeProduct(Product product, Integer count) {
		products.put(product, products.get(product) - count);
	}

	public Product getProductBySerial(int serial) {

		for (Map.Entry<Product, Integer> product : products.entrySet()) {
			if (product.getKey().getSerialNumber() == serial) {
				return product.getKey();
			}
		}

		return null;
	}
}
