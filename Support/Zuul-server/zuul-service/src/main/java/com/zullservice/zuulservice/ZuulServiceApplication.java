package com.zullservice.zuulservice;

import com.zullservice.zuulservice.filter.pre.SimpleFilter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableDiscoveryClient
@EnableZuulProxy
@SpringBootApplication
public class ZuulServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulServiceApplication.class, args);
	}

	// public SimpleFilter simpleFilter() {
	// 	return new SimpleFilter();
	// }
	// @Bean
	// public WebMvcConfigurer corsConfigurer() {
	// 	return new WebMvcConfigurer(){
	// 		public void addCorsMappings(CorsRegistry registry) {
	// 			registry.addMapping("/path-1/**")
	// 					.allowedOrigins("https://allowed-origin.com")
	// 					.allowedMethods("GET", "POST");
	// 		}
	// 	};
}
	

