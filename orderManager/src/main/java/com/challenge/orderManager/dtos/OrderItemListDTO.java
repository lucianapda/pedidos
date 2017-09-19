package com.challenge.orderManager.dtos;

import java.util.List;

import com.challenge.orderManager.dtos.OrderListDTO.OrderListDTOBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemListDTO {
	private List<OrderItemDTO> orderItemList;
}
