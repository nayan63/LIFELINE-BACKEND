package com.lifeline.ApiGateway.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Configuration
public class APIConfig {

    @Bean
    public RestTemplate restTemplate()
    {
        return new RestTemplate();
    }

}
