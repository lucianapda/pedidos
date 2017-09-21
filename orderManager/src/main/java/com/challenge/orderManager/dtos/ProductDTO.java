package com.challenge.orderManager.dtos;

import java.math.BigInteger;

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

	private long id;
	private String name;
	private double price;
	
	public ProductDTO(Product product) {
		this.id = product.getProduct_id();
		this.name = product.getName();
		this.price = product.getPrice();
	}

	public Product toEntity() {
		return Product.builder().product_id(this.id).name(this.name).price(this.price).build();
	}
}
