package com.challenge.orderManager.dtos;

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
public class OrderItemDTO {

	
     	
     	private ProductDTO product;
		private double amount;		
		private long id;
		private OrderDTO order;
		
		public OrderItemDTO(ProductDTO productDTO, OrderDTO orderDTO, double amount, long id) {
			this.amount = amount;
			this.product = productDTO;
			this.order = orderDTO;
			this.id = id;
		}
		
		public OrderItemDTO(Product product, Order order, double amount, long id) {
			this.amount = amount;
			this.product = new ProductDTO(product);
//			this.order = new OrderDTO(order);
			this.id = id;
		}
		
		public OrderItem toEntity() {
			return OrderItem.builder()
					.product(this.product.toEntity())
					.orderx(this.order.toEntity())
					.amount(this.amount)
					.id(this.id)
					.build();
		}
		
		
	}


