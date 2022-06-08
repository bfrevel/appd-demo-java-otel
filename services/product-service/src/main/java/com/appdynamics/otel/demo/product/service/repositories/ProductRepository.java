package com.appdynamics.otel.demo.product.service.repositories;

import java.util.UUID;

import com.appdynamics.otel.demo.product.service.entities.Product;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, UUID> {
}