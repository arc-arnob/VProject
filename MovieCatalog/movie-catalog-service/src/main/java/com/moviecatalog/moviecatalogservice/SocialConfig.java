package com.moviecatalog.moviecatalogservice;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;


@Configuration
public class SocialConfig extends WebSecurityConfigurerAdapter{

	@Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http.authorizeRequests()
         .anyRequest().authenticated()
         .and()
         .oauth2Login();
    }

   
    
}