package com.challenge.orderManager.dtos;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.challenge.orderManager.entities.Order;
import com.challenge.orderManager.entities.OrderItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
	
	private List<OrderItemDTO> orderArray = new ArrayList();
	private double totalOrder;
	private long orderId;
	private int table;
	private double amoutItem;
	private Date orderDate;

		
	public OrderDTO (OrderDTO order, OrderItem orderItem){
		this.orderId = order.getOrderId();
		this.orderArray = order.getOrderArray();
		this.table = order.getTable();
		this.totalOrder = orderItem.getAmount() * orderItem.getProduct().getPrice();
		this.orderDate = order.getOrderDate();
		this.amoutItem = orderItem.getAmount();
	}
	
	public OrderDTO (Order order){
		this.orderId = order.getOrder_id();
		this.amoutItem = getTotalOrder();
		this.table = order.getTablex();		
		this.orderDate = order.getOrderDate();
		
		this.orderArray = convertOrderItemEntityToDTO(order.getProducts());
	}
	
	private List<OrderItemDTO> convertOrderItemEntityToDTO(List<OrderItem> entityList) {
		List<OrderItemDTO> dto = new ArrayList();
		if (entityList == null) {
			return null;
		}
		for (OrderItem entity : entityList) {
			dto.add(new OrderItemDTO(entity.getProduct(), entity.getOrderx(), entity.getAmount(), entity.getId()));
		}
		return dto;
	}
	
	public Order toEntity() {
		List<OrderItem> orderItemList = new ArrayList();
		if (this.orderArray != null) {
			for (OrderItemDTO dto : this.orderArray) {
				if (dto != null) {					
					orderItemList.add(dto.toEntity());
				}			
			}
			return Order.builder()
					.order_id(this.orderId)
					.tablex(this.table)
					.totalOrder(this.totalOrder)
					.orderDate(this.orderDate)
					.products(orderItemList)
					.build();
		}
		return Order.builder()
				.order_id(this.orderId)
				.tablex(this.table)
				.totalOrder(this.totalOrder)
				.orderDate(this.orderDate)
				.products(null)
				.build();
	}

}
