package com.challenge.orderManager.interactions;

import java.util.ArrayList;
import java.util.List;

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
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	public OrderDTO getOrderId(long orderId) {
		Order responseOrder = repository.getOrder(orderId);
		responseOrder.setProducts(orderItemRepository.getOrderItemList(orderId));
		return new OrderDTO(responseOrder);
	}

	public Order save(OrderDTO order) throws Exception {
		isValidOrder(order);
		Order persist = new Order();
		persist.setOrder_id(0);
		persist.setPaid_account(false);
		persist.setTablex(order.getTable());
		persist.setOrderDate(order.getOrderDate());
		Order merge = repository.save(persist);
		List<OrderItem> orderItemList = new ArrayList();
		OrderDTO newOrderDTO = new OrderDTO(merge);
		for (OrderItemDTO dto : order.getOrderArray()) {
			if (dto != null) {				
				OrderItemDTO orderDTO = new OrderItemDTO(dto.getProduct(), dto.getAmount(), dto.getId(), newOrderDTO);
				orderItemList.add(orderDTO.toEntity());
			}
		}
		merge.setProducts(orderItemList);
//		return repository.save(order.toEntity());
		repository.save(merge);
		Order responseOrder = repository.getOrder(merge.getOrder_id());
		responseOrder.setProducts(orderItemRepository.getOrderItemList(merge.getOrder_id()));
		return responseOrder;
	}

	private void isValidOrder(OrderDTO order) throws Exception {

		if (order.getAmoutItem() == 0) {
			throw new Exception("Campo amount é obrigatório");
		}

		if (order.getTable() == 0) {
			throw new Exception("Campo mesa é obrigatório");
		}
	}

}
