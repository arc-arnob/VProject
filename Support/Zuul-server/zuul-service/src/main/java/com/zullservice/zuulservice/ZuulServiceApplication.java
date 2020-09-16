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
// 	@Bean
//   	public FilterRegistrationBean corsFilter() {
//     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//     CorsConfiguration config = new CorsConfiguration();
//     config.setAllowCredentials(true);
//     config.addAllowedOrigin("*");
//     config.addAllowedHeader("*");
//     config.addAllowedMethod("*");
//     source.registerCorsConfiguration("/**", config);
//     FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
//     bean.setOrder(0);
//     return bean;
//   }
	
}
