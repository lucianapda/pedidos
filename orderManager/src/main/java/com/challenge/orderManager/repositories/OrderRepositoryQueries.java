package com.challenge.orderManager.repositories;

import java.math.BigInteger;
import java.util.List;

import com.challenge.orderManager.entities.Order;

public interface OrderRepositoryQueries {
	Order getOrder(long orderId);
	List<Order> getOrderList();
}
