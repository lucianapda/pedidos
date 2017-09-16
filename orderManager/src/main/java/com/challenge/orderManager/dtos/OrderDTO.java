package com.challenge.orderManager.dtos;

import java.util.ArrayList;

import com.challenge.orderManager.dtos.OrderItemDTO.OrderItemDTOBuilder;
import com.challenge.orderManager.entities.Order;
import com.challenge.orderManager.entities.OrderItem;
import com.challenge.orderManager.entities.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
	
	private ArrayList<OrderItemDTO> orderArray = new ArrayList();
	private double totalOrder;
	private int orderId;
	private int table;
	
	public OrderDTO (OrderDTO order, OrderItemDTO orderItem){
		this.orderId = order.getOrderId();
		this.orderArray = order.getOrderArray();
		this.table = order.getTable();
		this.totalOrder = order.getTotalOrder();
		
	}
	
	
	public Order toEntity() {
		return Order.builder().id(this.orderId).table(this.table).totalOrder(this.totalOrder).build();
	}

}
