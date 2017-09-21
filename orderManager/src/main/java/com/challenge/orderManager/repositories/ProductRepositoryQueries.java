package com.challenge.orderManager.repositories;

import java.math.BigInteger;
import java.util.List;

import com.challenge.orderManager.entities.Product;

public interface ProductRepositoryQueries {	
	Product getProduct(long productId);
	List<Product> getProductList();
}
