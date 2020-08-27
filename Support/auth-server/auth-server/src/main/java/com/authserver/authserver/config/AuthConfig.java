package com.authserver.authserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthConfig extends AuthorizationServerConfigurerAdapter {

   
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void configure(
        ClientDetailsServiceConfigurer clients)
        throws Exception {
        clients
            .inMemory()
            .withClient("authserver")
            .secret(passwordEncoder.encode("passwordforauthserver"))
            .redirectUris("http://localhost:8761")
            .authorizedGrantTypes("authorization_code",
                "refresh_token","password")
            .scopes("myscope")
            .autoApprove(true)
            .accessTokenValiditySeconds(30)
            .refreshTokenValiditySeconds(1800);
    }

    @Override
    public void configure(
        AuthorizationServerEndpointsConfigurer endpoints)
        throws Exception {
        endpoints
            .userDetailsService(userDetailsService);
    }

}