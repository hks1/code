package com.example.hplus.controllers;

import com.example.hplus.beans.Product;
import com.example.hplus.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/search")
    public String search(@RequestParam("search") String search, Model model){
        System.out.println("in search controller!!!");
        System.out.println("search criteria: " + search);
        List<Product> products = new ArrayList<>();
        products = productRepository.searchByName(search);
        products.stream().forEach(product -> System.out.println(product));
        model.addAttribute("products", products);
        return "search";
    }
}
