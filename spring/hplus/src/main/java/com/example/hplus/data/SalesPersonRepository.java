package com.example.hplus.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalesPersonRepository extends JpaRepository<SalesPerson, Long> {
    public List<SalesPerson> findByLastName(String lastName);
    public SalesPerson findByEmail(String email);
}
