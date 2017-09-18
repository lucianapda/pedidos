package com.challenge.orderManager.dtos;

import com.challenge.orderManager.entities.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

	private int id;
	private String name;
	private double price;
	
	public ProductDTO(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.price = product.getPrice();
	}

	public Product toEntity() {
		return Product.builder().id(this.id).name(this.name).price(this.price).build();
	}
}
