package com.orderandnotification.orderandnotification.models.prodcut.logic;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.orderandnotification.orderandnotification.models.prodcut.Product;
import com.orderandnotification.orderandnotification.models.prodcut.ProductsRepository;

@Service
public class ProductRepositorybsl {
	private final ProductsRepository repository;

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
			categories.put(entry.getKey().getCategory(), categories.getOrDefault(entry.getKey().getCategory(), 0) + entry.getValue());
		}

		return categories;
	}

	public void removeProducts(Map<Product, Integer> prodcutsToRemove){
		// updating the repository
		for(Map.Entry<Product, Integer> product : prodcutsToRemove.entrySet()){
			repository.removeProduct(product.getKey() , product.getValue()); 
		}

	}

	public double verifyOrders(Map<Product,Integer> customerOrder , Map<String, Integer> products){
		double totalCost = 0.0;
		for (Map.Entry<String, Integer> product : products.entrySet()) {

			boolean productFound = false;

			for (Map.Entry<Product, Integer> entry : getProducts().entrySet()) {

				if (product.getKey() == entry.getKey().getName()) {
					productFound = true;
					// insufficient category number
					if (entry.getValue() < product.getValue()) {
						return -2;
//						return "We have only " + entry.getValue() + " from this product " + product.getKey();
					}
					totalCost += (product.getValue() * entry.getKey().getPrice());
					// add
					customerOrder.put(entry.getKey() , product.getValue());
				}
				
			}

			if(!productFound){
				return -1;
			}
		}
		return totalCost;
	}

	public Product getProduct(int serial) {
		return repository.getProductBySerial(serial);
	}
}
