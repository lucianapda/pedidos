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

import com.challenge.orderManager.dtos.OrderItemDTO;
import com.challenge.orderManager.dtos.OrderItemListDTO;
import com.challenge.orderManager.dtos.ProductDTO;
import com.challenge.orderManager.dtos.ProductListDTO;
import com.challenge.orderManager.entities.OrderItem;
import com.challenge.orderManager.entities.Product;
import com.challenge.orderManager.interactions.OrderItemAdition;
import com.challenge.orderManager.interactions.ProductAdition;
import com.challenge.orderManager.repositories.OrderItemRepository;
import com.challenge.orderManager.repositories.ProductRepository;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/orderItem")
public class OrderItemController {

//	@Autowired
//	private OrderItemRepository orderItemRepository;
//	
//
//	@Autowired
//	private OrderItemAdition orderItemAdition;
//	
//	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public OrderItemDTO get(@PathVariable String orderItemId) {
//		OrderItem orderItem = orderItemRepository.getOrderItem(orderItemId);
//		return new OrderItemDTO(orderItem);		
//	}
//	
//	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public OrderItemListDTO list() {
//		return new OrderItemListDTO(orderItemRepository.getOrderItemList().stream().map( OrderItemDTO::new).collect( Collectors.toList() ));			
//	}
//
//	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//	public OrderItemDTO save(@RequestBody OrderItemDTO orderItemDto) throws Exception {
//		OrderItem orderItem = orderItemAdition.save(orderItemDto);
//		return new OrderItemDTO(orderItem);
//	}
//
//	@RequestMapping(value = "/orderItemId}", method = RequestMethod.DELETE)
//	public void delete(@PathVariable String orderItem) {
//		orderItemRepository.delete( orderItem );
//	}
//	
//	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
//	public OrderItemDTO merge(@RequestBody OrderItemDTO orderItemDto) throws Exception {
//		OrderItem orderItem = orderItemAdition.save(orderItemDto);
//		return new OrderItemDTO();
//	}
	
}
