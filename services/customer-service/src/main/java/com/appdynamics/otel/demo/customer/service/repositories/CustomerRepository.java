package com.appdynamics.otel.demo.customer.service.repositories;

import java.util.UUID;

import com.appdynamics.otel.demo.customer.service.entities.Customer;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, UUID> {
}