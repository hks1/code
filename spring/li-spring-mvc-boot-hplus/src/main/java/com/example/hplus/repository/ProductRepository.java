package com.example.hplus.repository;

import com.example.hplus.beans.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    @Query(value = "select * from product p where p.name like %:name%", nativeQuery = true)
    public List<Product> searchByName(@Param("name") String name);
}
