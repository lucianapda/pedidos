package com.challenge.orderManager.controllers;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.orderManager.dtos.OrderDTO;
import com.challenge.orderManager.dtos.OrderItemDTO;
import com.challenge.orderManager.dtos.OrderItemListDTO;
import com.challenge.orderManager.dtos.OrderListDTO;
import com.challenge.orderManager.entities.Order;
import com.challenge.orderManager.entities.OrderItem;
import com.challenge.orderManager.interactions.OrderAdition;
import com.challenge.orderManager.interactions.OrderItemAdition;
import com.challenge.orderManager.repositories.OrderItemRepository;
import com.challenge.orderManager.repositories.OrderRepository;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/order")
public class OrderController {
	@Autowired
	private OrderRepository orderRepository;
	

	@Autowired
	private OrderAdition orderAdition;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public OrderDTO get(@PathVariable String orderId) {
		Order order = orderRepository.getOrder(orderId);
		return new OrderDTO(order);		
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public OrderListDTO list() {
		return new OrderListDTO(orderRepository.getOrderList().stream().map( OrderDTO::new).collect( Collectors.toList() ));			
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public OrderDTO save(@RequestBody OrderDTO orderDto) throws Exception {
		Order order = orderAdition.save(orderDto);
		return new OrderDTO(order);
	}

	@RequestMapping(value = "/orderId}", method = RequestMethod.DELETE)
	public void delete(@PathVariable String order) {
		orderRepository.delete( order );
	}
	
	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public OrderDTO merge(@RequestBody OrderDTO orderDto) throws Exception {
		Order order = orderAdition.save(orderDto);
		return new OrderDTO();
	}
	
	
}
