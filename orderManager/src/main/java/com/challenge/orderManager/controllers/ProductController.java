package com.challenge.orderManager.controllers;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.orderManager.dtos.ProductDTO;
import com.challenge.orderManager.dtos.ProductListDTO;
import com.challenge.orderManager.entities.Product;
import com.challenge.orderManager.interactions.product.ProductAdition;
import com.challenge.orderManager.repositories.ProductRepository;

@RestController
@CrossOrigin
@RequestMapping(value = "/product")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductAdition productAdition;

	@RequestMapping(value = "/{productId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductDTO get(@PathVariable String productId) {
		Product product = productRepository.getProduct(productId);
		return new ProductDTO(product.getId(), product.getName(), product.getPrice());		
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductListDTO list() {
		return new ProductListDTO(productRepository.getProductList().stream().map( ProductDTO::new).collect( Collectors.toList() ));			
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductDTO save(@RequestBody ProductDTO productDto) {
		Product product = productAdition.save(productDto.toEntity());
		return new ProductDTO(product.getId(), product.getName(), product.getPrice());
	}

	@RequestMapping(value = "/{productId}", method = RequestMethod.DELETE)
	public void delete(@PathVariable String productId) {
		productRepository.delete( productId );
	}
	
	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductDTO merge(@RequestBody ProductDTO productDto) {
		Product product = productAdition.save(productDto.toEntity());
		return new ProductDTO(product.getId(), product.getName(), product.getPrice());
	}
}
