package com.challenge.orderManager.repositories;

import java.util.List;

import com.challenge.orderManager.entities.Order;

public interface OrderRepositoryQueries {
	Order getOrder(int orderId);
	List<Order> getOrderList();
}
