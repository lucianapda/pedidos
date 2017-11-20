package com.challenge.orderManager.dtos;

import java.util.List;

import com.challenge.orderManager.dtos.PedidoDTO.PedidoDTOBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoPago {
	private long orderId;
	private int mesa;

}
