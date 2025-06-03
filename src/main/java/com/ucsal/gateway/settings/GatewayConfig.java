package com.ucsal.gateway.settings;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import com.ucsal.gateway.components.JwtAuth;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@Configuration
public class GatewayConfig {
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("pessoas-service", r -> r.path("/pessoas/**").filters(f -> f.stripPrefix(1)).uri("lb://PESSOAS-SERVICE"))
				.route("projetos-service", r -> r.path("/projetos/**").uri("lb://PROJETOS-SERVICE"))
				.route("grupos-service", r -> r.path("/grupos/**").uri("lb://GRUPOS-SERVICE"))
				//.route("catch_all", r -> r.path("/**").uri("http://localhost:8080"))
				.build();
	}
}
