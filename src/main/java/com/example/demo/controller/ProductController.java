//package com.example.demo.controller;
//
//import com.example.demo.model.Product;
//import com.example.demo.repository.ProductRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/products")
//@RequiredArgsConstructor
//public class ProductController {
//
//    private final ProductRepository productRepository;
//
//    @GetMapping
//    public List<Product> getAllProducts() {
//        return productRepository.findAll();
//    }
//}
