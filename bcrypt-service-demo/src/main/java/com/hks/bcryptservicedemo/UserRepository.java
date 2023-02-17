package com.hks.bcryptservicedemo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    //@Query("select u from User u where email = ?1")
    @Query("select u from User u where email = :email")
    public Optional<User> findByEmail(String email);
}
