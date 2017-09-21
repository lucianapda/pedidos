package com.challenge.orderManager.dtos;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.challenge.orderManager.entities.Pedido;
import com.challenge.orderManager.entities.PedidoProduto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

	private List<PedidoProdutoDTO> orderArray = new ArrayList();	
	private long orderId;	
	
	public PedidoDTO(Pedido pedido) {
		this.orderId = pedido.getId();
		List<PedidoProdutoDTO> dto = new ArrayList();
		if (pedido.getProducts() != null) {
			for (PedidoProduto entity : pedido.getProducts()) {
				dto.add(new PedidoProdutoDTO(entity));
			}
		}		
		this.orderArray = dto;
	}		
}
