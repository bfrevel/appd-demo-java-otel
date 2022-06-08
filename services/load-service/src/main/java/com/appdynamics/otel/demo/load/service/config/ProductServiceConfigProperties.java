package com.appdynamics.otel.demo.load.service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties("services.product")
public class ProductServiceConfigProperties {
    private String host;
    private String port;
}
