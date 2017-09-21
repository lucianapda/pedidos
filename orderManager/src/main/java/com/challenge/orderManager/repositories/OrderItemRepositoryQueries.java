package com.challenge.orderManager.repositories;

import java.math.BigInteger;
import java.util.List;


import com.challenge.orderManager.entities.OrderItem;

public interface OrderItemRepositoryQueries {

	OrderItem getOrderItem(long orderItemId);
	List<OrderItem> getOrderItemList(long orderId);
}
