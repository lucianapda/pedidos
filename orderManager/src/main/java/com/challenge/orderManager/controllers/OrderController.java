package com.challenge.orderManager.controllers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
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
import com.challenge.orderManager.dtos.PedidoDTO;
import com.challenge.orderManager.dtos.PedidoProdutoDTO;
import com.challenge.orderManager.dtos.ProductDTO;
import com.challenge.orderManager.entities.Order;
import com.challenge.orderManager.entities.OrderItem;
import com.challenge.orderManager.entities.Pedido;
import com.challenge.orderManager.entities.PedidoProduto;
import com.challenge.orderManager.entities.Product;
import com.challenge.orderManager.interactions.OrderAdition;
import com.challenge.orderManager.interactions.OrderItemAdition;
import com.challenge.orderManager.repositories.OrderItemRepository;
import com.challenge.orderManager.repositories.OrderRepository;
import com.challenge.orderManager.repositories.PedidoRepository;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/order")
public class OrderController {
	@Autowired
	private OrderRepository orderRepository;
	

	@Autowired
	private OrderAdition orderAdition;
	
	@Autowired
	private OrderItemAdition pedidoProdutoRepos;		
	
	@RequestMapping(value = "/{orderId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public OrderDTO get(@PathVariable long orderId) {
		return orderAdition.getOrderId(orderId);
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public OrderListDTO list() {
		return new OrderListDTO(orderRepository.getOrderList().stream().map( OrderDTO::new).collect( Collectors.toList() ));			
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public PedidoDTO save(@RequestBody PedidoDTO pedidoDTO) throws Exception {
		Pedido pedido = pedidoProdutoRepos.salva(pedidoDTO); 
//				pedidoRepository.save(convertDTOToEntity(pedidoDTO));
		return new PedidoDTO(pedido);
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
	
	
//	public Pedido convertDTOToEntity(PedidoDTO pedido) {
//		Pedido entity = new Pedido();
//		List<PedidoProduto> pedidoProduto = new ArrayList();
//		for (PedidoProdutoDTO dto : pedido.getOrderArray()) {
//			PedidoProduto pp = new PedidoProduto();
//			pp.setId(dto.getId());
//			pp.setProduto(convertProduct(dto.getProduto()));
//			pp.setQuantidade(2D);			
//		}
//		entity.setProducts(pedidoProduto);
//		return entity;
//	}
//	
//	public Product convertProduct(ProductDTO produto) {
//		Product pro = new Product();
//		pro.setName(produto.getName());
//		pro.setPrice(produto.getPrice());
//		pro.setProduct_id(produto.getId());
//		return pro;
//	}
	
}
