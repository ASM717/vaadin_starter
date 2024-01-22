package com.example.demo.service;

import com.example.demo.mapper.ProductRowMapper;
import com.example.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> getAllProducts() {
        // Получаем все продукты из базы данных
        String selectSql = "SELECT * FROM product";
        return jdbcTemplate.query(selectSql, new ProductRowMapper());
    }

    public void addProduct(Product product) {
        // Добавляем новый продукт в базу данных
        String insertSql = "INSERT INTO product (name, price) VALUES (?, ?)";
        jdbcTemplate.update(insertSql, product.getName(), product.getPrice());
    }

}
