package com.challenge.orderManager.dtos;

import java.util.List;

import com.challenge.orderManager.dtos.ProductListDTO.ProductListDTOBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderListDTO {
	private List<OrderDTO> orderList;
}
