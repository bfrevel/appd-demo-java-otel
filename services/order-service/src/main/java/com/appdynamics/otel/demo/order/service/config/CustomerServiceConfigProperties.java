package com.appdynamics.otel.demo.order.service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties("services.customer")
public class CustomerServiceConfigProperties {
    private String host;
    private String port;
}
