package com.ucsal.gateway.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class APIController {
	
	private final WebClient web = WebClient.create();
	
	@GetMapping
	 public Mono<Map<String, Object>> getStatuses() {
		
		return Mono.zip(
                getStatus("pessoas", "http://localhost:8081/health"),
                getStatus("projetos", "http://localhost:8082/health"),
                getStatus("grupos", "http://localhost:8083/health")
        ).map(tuple -> {
            Map<String, Object> statusMap = new HashMap<>();
            statusMap.put("pessoas", tuple.getT1());
            statusMap.put("projetos", tuple.getT2());
            statusMap.put("grupos", tuple.getT3());
            return statusMap;
        });
		
		//return ResponseEntity.ok("API Operacional");
	}
	
	private Mono<String> getStatus(String service, String url) {
        return web
                .get()
                .uri(url)
                .retrieve()
                .toBodilessEntity()
                .map(response -> "ONLINE")
                .onErrorReturn("OFFLINE");
    }
}
