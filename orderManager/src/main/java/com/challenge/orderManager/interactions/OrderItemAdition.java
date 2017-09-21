package com.challenge.orderManager.interactions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.orderManager.dtos.OrderItemDTO;
import com.challenge.orderManager.dtos.PedidoDTO;
import com.challenge.orderManager.dtos.PedidoProdutoDTO;
import com.challenge.orderManager.dtos.ProductDTO;
import com.challenge.orderManager.entities.OrderItem;
import com.challenge.orderManager.entities.Pedido;
import com.challenge.orderManager.entities.PedidoProduto;
import com.challenge.orderManager.entities.Product;
import com.challenge.orderManager.repositories.OrderItemRepository;
import com.challenge.orderManager.repositories.PedidoProdutoRepository;
import com.challenge.orderManager.repositories.PedidoRepository;
import com.challenge.orderManager.repositories.PedidoRepositoryQueriesImpl;
import com.challenge.orderManager.repositories.ProductRepository;

@Service
public class OrderItemAdition {	
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private PedidoProdutoRepository pedidoProdutoRepository;
		
	private PedidoRepositoryQueriesImpl buscaPedido = new PedidoRepositoryQueriesImpl();
	
	public Pedido salva(PedidoDTO pedidoDTO) {
		Pedido persist = new Pedido();
		persist.setId(0);
		persist = pedidoRepository.save(persist);
		return convertDTOToEntity(pedidoDTO, persist);		
	}
	
	private void isValidOrderItem(OrderItemDTO orderItem) throws Exception {
		if (orderItem.getId() == 0) {
			throw new Exception("Campo id é obrigatorio");
		}
		
		if (orderItem.getAmount() == 0){
			throw new Exception("Campo amount é obrigatório");
		}				
		
		if (orderItem.getProduct() == null){
			throw new Exception("Campo produto é obrigatório");
			
		}
		
		if (orderItem.getOrder() == null){
			throw new Exception("Campo pedido é obrigatório");
		}
	}
	
	public Pedido convertDTOToEntity(PedidoDTO pedido, Pedido persist) {
		Pedido entity = new Pedido();
		entity.setId(persist.getId());
		List<PedidoProduto> pedidoProduto = new ArrayList();
		ArrayList<Long> listaIds = new ArrayList<>();
		for (PedidoProdutoDTO dto : pedido.getOrderArray()) {
			PedidoProduto pp = new PedidoProduto();
			pp.setId(dto.getId());
			pp.setProduto(productRepository.getProduct(dto.getProduto().getId()));
			pp.setProduto(convertProduct(dto.getProduto()));
			pp.setQuantidade(2D);
			pp.setPedido(persist);
			listaIds.add(pedidoProdutoRepository.save(pp).getId());
		}
		entity.setProducts(pedidoProduto);		
		List<PedidoProduto> pedidoProdutoEntity = new ArrayList<>();
		for (Long id : listaIds) {
			pedidoProdutoEntity.add(pedidoProdutoRepository.findOne(id));
		}
		Pedido xpto = new Pedido();
		xpto = pedidoRepository.findOne(entity.getId());
		xpto.setProducts(pedidoProdutoEntity);
		return xpto;
	}
	
	public Product convertProduct(ProductDTO produto) {
		Product pro = new Product();
		pro.setName(produto.getName());
		pro.setPrice(produto.getPrice());
		pro.setProduct_id(produto.getId());
		return pro;
	}
}
