package com.appdynamics.otel.demo.order.service.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderPosition {
    private int quantity;
    private Product product;
}
