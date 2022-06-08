package com.appdynamics.otel.demo.load.service.components;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import com.appdynamics.otel.demo.load.service.config.CustomerServiceConfigProperties;
import com.appdynamics.otel.demo.load.service.config.OrderServiceConfigProperties;
import com.appdynamics.otel.demo.load.service.config.ProductServiceConfigProperties;
import com.appdynamics.otel.demo.load.service.entities.Order;
import com.appdynamics.otel.demo.load.service.entities.OrderPosition;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
@RequiredArgsConstructor
public class LoadScheduler {

        private final CustomerServiceConfigProperties customerServiceConfigProperties;
        private final ProductServiceConfigProperties productServiceConfigProperties;
        private final OrderServiceConfigProperties orderServiceConfigProperties;
        private final RestTemplate restTemplate = new RestTemplate();

        private static String SERVICE_URL = "http://%s:%s/api/%s";
        private static String HEALTH_URL = "http://%s:%s/actuator/health";

        private static String HEALTH_LOG_MSG = "[x] {} health: '{}'";
        private static String ORDER_LOG_MSG = "[x] created order: '{}'";

        @Scheduled(fixedRate = 30000)
        public void testOrderService() {

                List<OrderPosition> orderPositions = new LinkedList<>();
                orderPositions.add(new OrderPosition(2, UUID.fromString("3071b172-4091-4278-996c-7211b9ced5e0")));
                orderPositions.add(new OrderPosition(3, UUID.fromString("d70a79a0-b446-49d7-ad1b-3d00b978edbc")));
                Order orderRequest = new Order(UUID.fromString("24932dcd-a40d-4644-8343-305345da3c38"),
                                orderPositions);

                String jsonString = this.restTemplate
                                .postForObject(String.format(SERVICE_URL, orderServiceConfigProperties.getHost(),
                                                orderServiceConfigProperties.getPort(), "order"), orderRequest,
                                                String.class);

                log.info(ORDER_LOG_MSG, jsonString);

        }

        @Scheduled(fixedRate = 30000)
        public void healthCheckCustomerService() {
                String health = this.restTemplate.getForObject(
                                String.format(HEALTH_URL, customerServiceConfigProperties.getHost(),
                                                customerServiceConfigProperties.getPort()),
                                String.class);
                log.info(HEALTH_LOG_MSG, "customer", health);
        }

        @Scheduled(fixedRate = 30000)
        public void healthCheckProductService() {
                String health = this.restTemplate.getForObject(
                                String.format(HEALTH_URL, productServiceConfigProperties.getHost(),
                                                productServiceConfigProperties.getPort()),
                                String.class);
                log.info(HEALTH_LOG_MSG, "product", health);
        }

        @Scheduled(fixedRate = 30000)
        public void healthCheckOrderService() {
                String health = this.restTemplate.getForObject(
                                String.format(HEALTH_URL, orderServiceConfigProperties.getHost(),
                                                orderServiceConfigProperties.getPort()),
                                String.class);
                log.info(HEALTH_LOG_MSG, "order", health);
        }

}
