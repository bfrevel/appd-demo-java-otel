package com.appdynamics.otel.demo.order.service.entities.request;

import java.util.UUID;

import lombok.Data;

@Data
public class OrderRequestPosition {
    private int quantity;
    private UUID productId;
}
