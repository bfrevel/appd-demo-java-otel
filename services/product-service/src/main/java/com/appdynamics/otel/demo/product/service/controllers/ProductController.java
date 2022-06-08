package com.appdynamics.otel.demo.product.service.controllers;

import java.util.Optional;
import java.util.UUID;

import com.appdynamics.otel.demo.product.service.entities.Product;
import com.appdynamics.otel.demo.product.service.repositories.ProductRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/product")
@Log4j2
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping(path = "/{id}")
    public Optional<Product> get(@PathVariable UUID id) {
        Optional<Product> product = productRepository.findById(id);
        log.info("[x] returning '{}}'", product);
        return product;
    }

}
