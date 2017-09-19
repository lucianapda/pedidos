package com.challenge.orderManager.interactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.orderManager.dtos.OrderItemDTO;
import com.challenge.orderManager.dtos.ProductDTO;
import com.challenge.orderManager.entities.OrderItem;
import com.challenge.orderManager.entities.Product;
import com.challenge.orderManager.repositories.OrderItemRepository;
import com.challenge.orderManager.repositories.ProductRepository;

@Service
public class OrderItemAdition {
	@Autowired
	private OrderItemRepository repository;

	public OrderItem save(OrderItemDTO orderItem) throws Exception {
		isValidOrderItem(orderItem);
		return repository.save(orderItem.toEntity());
	}
	
	private void isValidOrderItem(OrderItemDTO orderItem) throws Exception {
		if (orderItem.getId() == null) {
			throw new Exception("Campo id é obrigatorio");
		}
		
		if (orderItem.getAmount() == 0){
			throw new Exception("Campo amount é obrigatório");
		}
		
		if (orderItem.getPrice() == 0){
			throw new Exception("Campo preço é obrigatório");
		}
		
		if (orderItem.getProductId() == null){
			throw new Exception("Campo produto é obrigatório");
			
		}
		
		if (orderItem.getOrderId() == null){
			throw new Exception("Campo pedido é obrigatório");
		}
	}
}
