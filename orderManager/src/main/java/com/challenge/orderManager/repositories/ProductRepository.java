package com.challenge.orderManager.repositories;

import org.springframework.data.repository.CrudRepository;

import com.challenge.orderManager.entities.Product;

public interface ProductRepository extends ProductRepositoryQueries, CrudRepository<Product, String> {
}
