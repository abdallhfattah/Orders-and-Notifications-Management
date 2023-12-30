package com.orderandnotification.orderandnotification.models.prodcut.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orderandnotification.orderandnotification.models.prodcut.Product;
import com.orderandnotification.orderandnotification.models.prodcut.logic.ProductRepositorybsl;


@RestController
@RequestMapping("/repo")
public class ProductRepositoryController {
	private final ProductRepositorybsl repo;

	ProductRepositoryController(ProductRepositorybsl repo) {
		this.repo = repo;
	}

	@PostMapping("/add")
	public String postMethodName(@RequestBody Product entity) {
		return repo.addProduct(entity);
	}

	// @GetMapping("/get")
	// public List<Product> getProducts() {
	// 	List<Product> products = new ArrayList<>();
	// 	for(Map.Entry<Product, Integer> product : repo.getProducts().entrySet()){
	// 		products.add(product.getKey());
	// 	}
	// 	return products;
	// }

	@GetMapping("/get")
	public List<Object> getProducts() {
		List<Object> products = new ArrayList<>();
		for(Map.Entry<Product, Integer> product : repo.getProducts().entrySet()){
			products.add(product.getKey());
		}
		products.add(repo.getCategoryItems());
		return products;
	}
	
	// @GetMapping("/get/{ser}")
	// public Product getProduct(@PathVariable("ser") int serial) {
	// 	return repo.getProduct(serial);
	// }

	// @GetMapping("/getCategories")
	// public Map<String, Integer> getCategories() {
	// 	return repo.getCategoryItems();
	// }

}
