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

	
     	private Product product;
		private int amount;
		private double price ;
		private int id;
		
		
		public OrderItemDTO(OrderItemDTO orderItem){
			this.product = orderItem.getProduct();
			this.id = orderItem.getId();
			this.price = orderItem.getPrice();
			this.amount = orderItem.getAmount();
		}
		
		public OrderItem toEntity() {
			return OrderItem.builder().id(this.id).product(this.product).price(this.price).amount(this.amount).build();
		}
		
		
	}


