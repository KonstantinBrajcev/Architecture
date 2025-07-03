package com.gb.tickets.repositories;

import com.gb.tickets.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CustomerRepo extends JpaRepository<Customer, Integer> {
    public boolean existsByName(String name);
    Optional<Customer> findByName(String name);
}
