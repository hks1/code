package com.example.hplus.controller;

import com.example.hplus.data.Product;
import com.example.hplus.data.ProductRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class ProductController {
    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @QueryMapping
    public Iterable<Product> products(){
        return productRepository.findAll();
    }

    @QueryMapping
    public Product productById(@Argument String id){
        return productRepository.findById(id).orElseThrow();
    }
}
