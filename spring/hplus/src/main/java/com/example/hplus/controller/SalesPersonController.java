package com.example.hplus.controller;

import com.example.hplus.data.SalesPerson;
import com.example.hplus.data.SalesPersonRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class SalesPersonController {
    private SalesPersonRepository salesPersonRepository;

    public SalesPersonController(SalesPersonRepository salesPersonRepository){
        this.salesPersonRepository = salesPersonRepository;
    }

    @QueryMapping
    public Iterable<SalesPerson> salesPeople(){
        return this.salesPersonRepository.findAll();
    }

    @QueryMapping
    public SalesPerson salesPersonById(@Argument long id){
        return this.salesPersonRepository.findById(id).orElseThrow();
    }

    @QueryMapping
    public Iterable<SalesPerson> salesPersonByLastName(@Argument String lastName){
        return this.salesPersonRepository.findByLastName(lastName);
    }

    @QueryMapping
    public SalesPerson salesPersonByEmail(@Argument String email){
        return this.salesPersonRepository.findByEmail(email);
    }
}
