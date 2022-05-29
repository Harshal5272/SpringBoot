package com.sky.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sky.domain.Product;
import com.sky.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	
	private ProductService productService;

	@RequestMapping(value = "/product", method = RequestMethod.GET,consumes = "application/json", produces ="application/json")
	public @ResponseBody List<Product> getProducts() {
		return productService.getProducts();
	}

	@RequestMapping(value = "/product", method = RequestMethod.POST,consumes = "application/json", produces ="application/json")
	public @ResponseBody Product addproduct(@RequestBody Product product) {
		System.out.println(product);
		productService.addProduct(product);
		return product;
	}
	
	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET,consumes = "application/json", produces ="application/json")
	public @ResponseBody Product getProductById(@PathVariable("id") String id) {
	return productService.getProductById(id);
	}
	
	@RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE,consumes = "application/json", produces ="application/json")
	public @ResponseBody void deleteById(@PathVariable("id") String id) {
	 productService.deleteById(id);
	}
	
	@RequestMapping(value = "/product", method = RequestMethod.PUT,consumes = "application/json", produces ="application/json")
	public @ResponseBody void updateById(@RequestBody Product product) {
	 productService.updateById(product);
	}
}
