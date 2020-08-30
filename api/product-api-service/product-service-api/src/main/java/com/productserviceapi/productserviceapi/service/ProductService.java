package com.productserviceapi.productserviceapi.service;

import java.net.URI;
import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@EnableResourceServer
public class ProductService {
    

    //private static final Logger LOG = LoggerFactory.getLogger(ProductService.class);

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private LoadBalancerClient loadBalancer;

    @RequestMapping("/{urlId}")
    //@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<String> getProductComposite(@PathVariable String urlId) {

        //Debug
        //LOG.info("ProductApi: User={}, Auth={}, called with urlId={}", currentUser.getName(), authorizationHeader, urlId);
        
        
        URI uri = loadBalancer.choose("movie-catalog-service").getUri();
        String url = uri.toString() + "/catalog/" + urlId;
        // http://localhost:8081/catalog/showmovies
        
        //Debug
        //LOG.debug("GetProductComposite from URL: {}", url);

        ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);
        
        //Debug 
        //LOG.info("GetProductComposite http-status: {}", result.getStatusCode());
        //LOG.debug("GetProductComposite body: {}", result.getBody());

        return result;
    }
}