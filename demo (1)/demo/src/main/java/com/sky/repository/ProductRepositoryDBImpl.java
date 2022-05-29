package com.sky.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sky.domain.Product;

@Repository("ProductRepositoryDBImpl")
public class ProductRepositoryDBImpl  implements ProductRepository {

	private static String INSERT_PRODUCT_SQL = "insert into product(PRODUCT_ID,PRODUCT_NAME,PRODUCT_PRICE) values(?,?,?)";
	private static String ALL_PRODUCT_SQL = "select PRODUCT_ID,PRODUCT_NAME,PRODUCT_PRICE FROM PRODUCT";
	private static String GET_PRODUCT_BY_ID = "select PRODUCT_ID,PRODUCT_NAME,PRODUCT_PRICE FROM PRODUCT WHERE PRODUCT_ID=?";
	private static String DELETE_BY_ID = "delete  FROM PRODUCT WHERE PRODUCT_ID=?";
	private static String UPDATE_BY_ID = "UPDATE PRODUCT SET PRODUCT_NAME=?,PRODUCT_PRICE=? WHERE PRODUCT_ID=?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Override
	public void addProduct(Product product) {
		// TODO Auto-generated method stub
		jdbcTemplate.update(INSERT_PRODUCT_SQL, 
				product.getProductId(),product.getProductName(),product.getPrice());
	}

	@Override
	public List<Product> getProducts() {
		// TODO Auto-generated method stub
		
		return this.jdbcTemplate.query(ALL_PRODUCT_SQL, new ProductRowMapper());
	}
	
	private static final class ProductRowMapper implements RowMapper<Product>{
		public Product mapRow(ResultSet rs ,int rownum) throws SQLException{
			Product product=new Product();
			product.setProductId(rs.getString("PRODUCT_ID"));
			product.setProductName(rs.getString("PRODUCT_NAME"));
			product.setPrice(rs.getInt("PRODUCT_PRICE"));
			return product;
			
		}
	}

	@Override
	public Product getProductById(String id) {
		// TODO Auto-generated method stub
		
		return this.jdbcTemplate.queryForObject
				  ( GET_PRODUCT_BY_ID,
						    new Object[]{id}, new ProductRowMapper());
	}
	

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		this.jdbcTemplate.update(DELETE_BY_ID,id);
				
		
	}

	@Override
	public void updateById(Product newproduct) {
		// TODO Auto-generated method stub
		this.jdbcTemplate.update(UPDATE_BY_ID,newproduct.getProductName(),newproduct.getPrice(),newproduct.getProductId());
		
	}

}
