package com.challenge.orderManager.repositories;

import org.springframework.data.repository.CrudRepository;


import com.challenge.orderManager.entities.OrderItem;

public interface OrderItemRepository extends OrderItemRepositoryQueries, CrudRepository<OrderItem, String>  {

}
