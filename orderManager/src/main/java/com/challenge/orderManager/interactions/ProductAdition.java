package com.challenge.orderManager.interactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.orderManager.entities.Product;
import com.challenge.orderManager.repositories.ProductRepository;

@Service
public class ProductAdition {

	@Autowired
	private ProductRepository repository;

	public Product save(Product product) {
		return repository.save(product);
	}

}
