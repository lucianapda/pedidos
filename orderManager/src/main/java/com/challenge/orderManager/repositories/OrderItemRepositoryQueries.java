package com.challenge.orderManager.repositories;

import java.util.List;


import com.challenge.orderManager.entities.OrderItem;

public interface OrderItemRepositoryQueries {

	OrderItem getOrderItem(String orderItemId);
	List<OrderItem> getOrderItemList();
}
