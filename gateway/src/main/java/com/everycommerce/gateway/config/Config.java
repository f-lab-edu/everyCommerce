package com.everycommerce.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class Config {

//	@Bean
	public RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
		return builder.routes()
					/*.route(r -> r.path("/login")
						.filters(f ->f.addRequestHeader("login","login")
						.addResponseHeader(""))
					)*/
				.build();
	}
}
