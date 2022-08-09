package com.example.hplus.controller;

import com.example.hplus.data.Order;
import com.example.hplus.data.OrderRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class OrderController {
    private OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @QueryMapping
    public Iterable<Order> orders(){
        return orderRepository.findAll();
    }

    @QueryMapping
    public Order orderById(@Argument String id){
        return orderRepository.findById(id).orElseThrow();
    }
}
