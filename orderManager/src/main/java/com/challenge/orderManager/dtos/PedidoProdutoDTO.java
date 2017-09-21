package com.challenge.orderManager.dtos;

import java.util.List;

import com.challenge.orderManager.dtos.PedidoDTO.PedidoDTOBuilder;
import com.challenge.orderManager.entities.PedidoProduto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoProdutoDTO {

	private long id;
	private ProductDTO produto;
	
	public PedidoProdutoDTO(PedidoProduto entity) {
		this.id = entity.getId();
		this.produto = new ProductDTO(entity.getProduto());
	}
}
