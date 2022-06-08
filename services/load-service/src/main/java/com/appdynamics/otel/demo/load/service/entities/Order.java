package com.appdynamics.otel.demo.load.service.entities;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Order {
    private UUID customerId;
    private List<OrderPosition> positions;

}
