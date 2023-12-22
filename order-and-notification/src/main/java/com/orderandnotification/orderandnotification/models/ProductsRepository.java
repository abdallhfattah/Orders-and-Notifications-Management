package com.orderandnotification.orderandnotification.models;

import java.util.ArrayList;
import java.util.List;

public class ProductsRepository {
	private static ProductsRepository repository;
	private List<Prodcut> prodcuts;

	private ProductsRepository() {
		prodcuts = new ArrayList<Prodcut>();
	}

	public ProductsRepository getInstance() {

		if (repository == null) {
			repository = new ProductsRepository();
		}

		return repository;
	}

	public List<Prodcut> getAvalibeProdcuts() {
		return this.prodcuts;
	}

	public void addProdcut(Prodcut prodcut) {
		prodcuts.add(prodcut);
	}
}
