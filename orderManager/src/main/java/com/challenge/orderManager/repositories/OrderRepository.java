package com.challenge.orderManager.repositories;

import org.springframework.data.repository.CrudRepository;

import com.challenge.orderManager.entities.Order;



public interface OrderRepository extends OrderRepositoryQueries, CrudRepository<Order, String> {
}
