package com.challenge.orderManager.interactions;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.orderManager.dtos.OrderItemDTO;
import com.challenge.orderManager.dtos.PedidoDTO;
import com.challenge.orderManager.dtos.PedidoPago;
import com.challenge.orderManager.dtos.PedidoProdutoDTO;
import com.challenge.orderManager.dtos.ProductDTO;
import com.challenge.orderManager.entities.Pedido;
import com.challenge.orderManager.entities.PedidoProduto;
import com.challenge.orderManager.entities.Product;
import com.challenge.orderManager.entities.QPedido;
import com.challenge.orderManager.repositories.PedidoProdutoRepository;
import com.challenge.orderManager.repositories.PedidoRepository;
import com.challenge.orderManager.repositories.PedidoRepositoryQueriesImpl;
import com.challenge.orderManager.repositories.ProductRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Service
public class OrderItemAdition {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private PedidoProdutoRepository pedidoProdutoRepository;

	@Autowired
	private EntityManager entityManager;

	private static final QPedido PEDIDOX = QPedido.pedido;

	private PedidoRepositoryQueriesImpl buscaPedido = new PedidoRepositoryQueriesImpl();

	public Pedido pagar(PedidoPago pagar) {
		Pedido pedido = pedidoRepository.findOne(pagar.getOrderId());
		pedido.setPago(1);
		return pedidoRepository.save(pedido);
	}

	public Pedido salva(PedidoDTO pedidoDTO) {
		Pedido persist = new Pedido();
		persist.setId(0);
		persist.setMesa(pedidoDTO.getMesa());
		persist.setPago(0);
		persist = pedidoRepository.save(persist);
		return convertDTOToEntity(pedidoDTO, persist);
	}

	public Pedido getPedido(int mesaId) {
		return new JPAQueryFactory(entityManager).selectFrom(PEDIDOX)
				.where(PEDIDOX.mesa.eq(mesaId).and(PEDIDOX.pago.eq(0))).fetchFirst();
	}

	public Pedido buscaMesa(int mesa) {
		Pedido pedido = new Pedido();
		pedido = getPedido(mesa);
		return pedido;
	}

	private void isValidOrderItem(OrderItemDTO orderItem) throws Exception {
		if (orderItem.getId() == 0) {
			throw new Exception("Campo id é obrigatorio");
		}

		if (orderItem.getAmount() == 0) {
			throw new Exception("Campo amount é obrigatório");
		}

		if (orderItem.getProduct() == null) {
			throw new Exception("Campo produto é obrigatório");

		}

		if (orderItem.getOrder() == null) {
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
			pp.setQuantidade(dto.getQuantidade());
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
