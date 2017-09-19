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

	
     	
     	private String productId;
		private int amount;
		private double price;
		private String id;
		private String orderId;
		
		
		
		public OrderItemDTO(OrderItem orderItem){
			this.productId = orderItem.getProductId();
			this.id = orderItem.getId();
			this.price = orderItem.getPrice();
			this.amount = orderItem.getAmount();
			this.orderId = orderItem.getOrderId();
		}
		
		
		public OrderItem toEntity() {
			return OrderItem.builder().productId(this.productId).price(this.price).amount(this.amount).orderId(this.orderId).build();
		}
		
		
	}


