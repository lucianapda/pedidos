package com.challenge.orderManager.interactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.orderManager.dtos.OrderDTO;
import com.challenge.orderManager.dtos.OrderItemDTO;
import com.challenge.orderManager.entities.Order;
import com.challenge.orderManager.entities.OrderItem;
import com.challenge.orderManager.repositories.OrderItemRepository;
import com.challenge.orderManager.repositories.OrderRepository;

@Service
public class OrderAdition {
	@Autowired
	private OrderRepository repository;

	public Order save(OrderDTO order) throws Exception {
		isValidOrder(order);
		return repository.save(order.toEntity());
	}
	
	private void isValidOrder(OrderDTO order) throws Exception {
		if (order.getOrderId() == null) {
			throw new Exception("Campo id é obrigatorio");
		}
		
		if (order.getAmoutItem() == 0){
			throw new Exception("Campo amount é obrigatório");
		}
		
		if (order.getTotalOrder() == 0){
			throw new Exception("Campo preço é obrigatório");
		}
		
		
		if (order.getTable() == 0){
			throw new Exception("Campo mesa é obrigatório");
		}
	}

}
