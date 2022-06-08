package com.appdynamics.otel.demo.order.service.controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.appdynamics.otel.demo.order.service.config.CustomerServiceConfigProperties;
import com.appdynamics.otel.demo.order.service.config.ProductServiceConfigProperties;
import com.appdynamics.otel.demo.order.service.entities.Customer;
import com.appdynamics.otel.demo.order.service.entities.Order;
import com.appdynamics.otel.demo.order.service.entities.OrderPosition;
import com.appdynamics.otel.demo.order.service.entities.Product;
import com.appdynamics.otel.demo.order.service.entities.request.OrderRequest;
import com.appdynamics.otel.demo.order.service.entities.request.OrderRequestPosition;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/order")
@Log4j2
@RequiredArgsConstructor
public class OrderController {

    private final CustomerServiceConfigProperties customerServiceConfigProperties;
    private final ProductServiceConfigProperties productServiceConfigProperties;
    private final RestTemplate restTemplate = new RestTemplate();

    private static String SERVICE_URL = "http://%s:%s/api/%s";
    private static String LOG_MSG = "[x] requested {}: '{}'";

    @PostMapping()
    public Optional<Order> post(@RequestBody OrderRequest orderRequest) {

        Customer customer = restTemplate
                .getForEntity(
                        String.format(SERVICE_URL, customerServiceConfigProperties.getHost(),
                                customerServiceConfigProperties.getPort(), "customer")
                                + "/{id}",
                        Customer.class, orderRequest.getCustomerId())
                .getBody();
        log.info(LOG_MSG, "customer", customer);

        List<OrderPosition> positions = new LinkedList<>();
        for (OrderRequestPosition requestPosition : orderRequest.getPositions()) {
            Product product = restTemplate
                    .getForEntity(String.format(SERVICE_URL,
                            productServiceConfigProperties.getHost(),
                            productServiceConfigProperties.getPort(), "product") + "/{id}",
                            Product.class,
                            requestPosition.getProductId())
                    .getBody();
            positions.add(new OrderPosition(requestPosition.getQuantity(), product));
            log.info(LOG_MSG, "product", product);
        }

        Order order = new Order(UUID.randomUUID(), customer, positions);

        log.info("order {}", order);
        return Optional.ofNullable(order);
    }

}
