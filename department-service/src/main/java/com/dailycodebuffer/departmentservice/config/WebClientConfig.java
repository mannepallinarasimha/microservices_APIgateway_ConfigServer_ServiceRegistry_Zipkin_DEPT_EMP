package com.dailycodebuffer.departmentservice.config;

import com.dailycodebuffer.departmentservice.client.EmployeeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {

    @Autowired
    private LoadBalancedExchangeFilterFunction loadBalancedExchangeFilterFunction;

    @Bean
    public WebClient createEmployeeWebClient(){
        return WebClient.builder()
                .baseUrl("http://employee-service")
                .filter(loadBalancedExchangeFilterFunction)
                .build();
    }

    @Bean
    public EmployeeClient employeeClient(){
        HttpServiceProxyFactory httpServiceProxyFactory =
                HttpServiceProxyFactory
                        .builder(WebClientAdapter.forClient(createEmployeeWebClient()))
                        .build();
        return httpServiceProxyFactory.createClient(EmployeeClient.class);
    }
}
