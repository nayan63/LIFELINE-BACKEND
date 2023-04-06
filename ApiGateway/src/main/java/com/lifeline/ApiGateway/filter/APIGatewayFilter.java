package com.lifeline.ApiGateway.filter;

import com.lifeline.ApiGateway.config.APIConfig;
import com.lifeline.ApiGateway.exceptionhandle.CustomException;
import com.lifeline.ApiGateway.util.JwtUtil;
import com.lifeline.ApiGateway.validator.RouteValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class APIGatewayFilter extends AbstractGatewayFilterFactory<APIGatewayFilter.Config> {

    @Autowired
    private RouteValidator validator;
    @Autowired
    private APIConfig apiConfig;
    @Autowired
    private JwtUtil util;

    private static Logger logger = LoggerFactory.getLogger(APIGatewayFilter.class);
    public APIGatewayFilter()
    {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            logger.info("Hello API Gateway");
            if (validator.isSecured.test(exchange.getRequest())) {
                logger.info("Hello Validator test");
                //header contains token or not
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    logger.info("1st Exception for missing Auth Header");
                    throw new CustomException("missing authorization header");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                logger.info("Checking Auth Header");
                logger.info(authHeader);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    logger.info(authHeader," Auth Header display");
                    authHeader = authHeader.substring(7);
                }
                try {
                    logger.info("Validating Auth Header");
//                    //REST call to AUTH service
//                    template.getForObject("http://IDENTITY-SERVICE//validate?token" + authHeader, String.class);
                    boolean isTrue = util.validateToken(authHeader);
                    if(!isTrue)
                    {
                        logger.info("Validate Auth Expired");
                        throw new CustomException("Token Expire");
                    }
                    logger.info("Validate Auth");

                } catch (Exception e) {
                    logger.info(e.getMessage());
                    throw new CustomException("Unauthorized access to application");
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {
    }
}
