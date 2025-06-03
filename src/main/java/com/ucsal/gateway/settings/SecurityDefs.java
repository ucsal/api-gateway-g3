package com.ucsal.gateway.settings;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.server.WebFilter;

import com.ucsal.gateway.components.JwtAuth;

import reactor.core.publisher.Mono;

@Configuration
public class SecurityDefs {

	@Bean
	public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http, JwtAuth jwtAuth) {
	    return http.csrf(csrf -> csrf.disable())
	        .authorizeExchange(exchanges -> exchanges
	        		.anyExchange().authenticated()
	        )
	        .addFilterAt(jwtAuth, SecurityWebFiltersOrder.AUTHENTICATION)
	        .build();
	}
}
