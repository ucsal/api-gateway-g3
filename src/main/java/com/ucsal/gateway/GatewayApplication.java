package com.ucsal.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

import com.ucsal.gateway.utils.JWT;

@SpringBootApplication
@ComponentScan
@EnableDiscoveryClient
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
		
		String Token = new JWT().CreateToken("identifier-master-cbZ1Wr4ezHFwA2AiZqSPnM8DFxlR80a8pRMlepi4BsLq1haIZeWQsA1ySokQivhV\n");
		System.out.println("----------------------------------------------");
		System.out.println("API Token: " + Token);
		System.out.println("----------------------------------------------");
	}

}
