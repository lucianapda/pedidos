package com.challenge.orderManager.repositories;

import java.util.List;

import com.challenge.orderManager.entities.Product;

public interface ProductRepositoryQueries {	
	Product getProduct(String productId);
	List<Product> getProductList();
}
