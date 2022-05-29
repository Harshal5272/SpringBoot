package com.sky.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sky.domain.Product;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
	private static List<Product> products = new ArrayList<Product>();

	@Override
	public void addProduct(Product product) {
		System.out.println("in repository");
		products.add(product);
	}

	@Override
	public List<Product> getProducts() {
		return products;
	}

	@Override
	public Product getProductById(String id) {
		for(Product product: products) {
			if (product.getProductId().equals(id)) {
				return product;
				
			}
		}		
		return null;
	}

	@Override
	public void deleteById(String id) {
		int index = 0;
		for(Product product: products) {
			if(product.getProductId().equals(id)) {
				 products.remove(index);
				
			}
			index =index+1;
		}
		
	}

	@Override
	public void updateById(Product newproduct) {
		int index = 0;
		for(Product product2: products) {
			if(product2.getProductId().equals(newproduct.getProductId())) {
				 products.set(index, newproduct);
				
			}
			index =index+1;
		}

	}

	

	

}
