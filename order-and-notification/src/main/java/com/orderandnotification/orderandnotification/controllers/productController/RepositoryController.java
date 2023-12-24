package com.orderandnotification.orderandnotification.controllers.productController;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orderandnotification.orderandnotification.logic.ProductRepositorybsl;
import com.orderandnotification.orderandnotification.models.Product;


@RestController
@RequestMapping("/repo")
public class RepositoryController {
	private final ProductRepositorybsl repo;

	RepositoryController(ProductRepositorybsl repo) {
		this.repo = repo;
	}

	@PostMapping("/add")
	public String postMethodName(@RequestBody Product entity) {
		return repo.addProdcut(entity);
	}

	@GetMapping("/get")
	public Map<Product , Integer> getProducts() {
		return repo.getProducts();
	}

	@GetMapping("/getCategorys")
	public Map<String,Integer> getCategorys() {
		return repo.getCategoryItems();
	}

}
