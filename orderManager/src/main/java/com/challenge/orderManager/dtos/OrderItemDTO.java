package com.challenge.orderManager.dtos;

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
public class OrderItemDTO {

	
     	
     	private int productId;
		private int amount;
		private double price;
		private int id;
		private int orderId;
		
		
		
		public OrderItemDTO(OrderItemDTO orderItem, OrderDTO orderId, Product product){
			this.productId = product.getId();
			this.id = orderItem.getId();
			this.price = orderItem.getPrice();
			this.amount = orderItem.getAmount();
			this.orderId = orderId.getOrderId();
		}
		
		
		public OrderItem toEntity() {
			return OrderItem.builder().id(this.id).productId(this.productId).price(this.price).amount(this.amount).orderId(this.orderId).build();
		}
		
		
	}


