package com.appdynamics.otel.demo.customer.service.controllers;

import java.util.Optional;
import java.util.UUID;

import com.appdynamics.otel.demo.customer.service.entities.Customer;
import com.appdynamics.otel.demo.customer.service.repositories.CustomerRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/customer")
@Log4j2
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerRepository customerRepository;

    @GetMapping(path = "/{id}")
    public Optional<Customer> get(@PathVariable UUID id) {
        Optional<Customer> customer = customerRepository.findById(id);
        log.info("[x] returning '{}}'", customer);
        return customer;
    }

}
