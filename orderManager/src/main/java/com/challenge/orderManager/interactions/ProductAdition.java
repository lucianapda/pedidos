package com.challenge.orderManager.interactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.orderManager.dtos.ProductDTO;
import com.challenge.orderManager.entities.Product;
import com.challenge.orderManager.repositories.ProductRepository;

@Service
public class ProductAdition {

	@Autowired
	private ProductRepository repository;

	public Product save(ProductDTO product) throws Exception {
		isValidProduct(product);
		return repository.save(product.toEntity());
	}
	
	private void isValidProduct(ProductDTO product) throws Exception {
		if (product.getName() == null) {
			throw new Exception("Campo nome é obrigatorio");
		}
		if (product.getName().trim().length() < 2) {
			throw new Exception("Nome do produto deve conter mais que 2 caracteres");
		}
		if (product.getName().length() > 50) {
			throw new Exception("Nome do produto não pode coter mais que 50 caracteres");
		}
		if (product.getPrice() == 0) {
			throw new Exception("O valor do produto não pode ser zero");
		}
		if (product.getPrice() < 0) {
			throw new Exception("O valor do produto não ser negativo");
		}
	}

}
