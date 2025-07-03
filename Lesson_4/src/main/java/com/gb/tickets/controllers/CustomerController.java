package com.gb.tickets.controllers;

import com.gb.tickets.models.Customer;
import com.gb.tickets.repositories.CustomerRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerRepo customerRepo;

    public CustomerController(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerRepo.findAll());
    }

    @PostMapping
    public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer) {
        if (customerRepo.existsByName(customer.getName())) {
            return ResponseEntity
                    .internalServerError()
                    .build();
        }
        Customer newCustomer = Customer.builder()
                .name(customer.getName())
                .password(customer.getPassword())
                .role(customer.getRole())
                .balance(1250f)
                .build();

        customerRepo.save(newCustomer);
        return ResponseEntity.ok(customerRepo
                .findByName(newCustomer.getName())
                .orElseThrow(
                        () -> new RuntimeException
                                ("Customer not found")
                ));
    }
}
