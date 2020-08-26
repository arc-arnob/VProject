package com.productserviceapi.productserviceapi.service;

import java.net.URI;
import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ProductService {

    private static final Logger LOG = LoggerFactory.getLogger(ProductService.class);

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private LoadBalancerClient loadBalancer;

    @RequestMapping("/{productId}")
    public ResponseEntity<String> getProductComposite(
        @PathVariable int productId,
        @RequestHeader(value="Authorization") String authorizationHeader,
        Principal currentUser) {

        LOG.info("ProductApi: User={}, Auth={}, called with productId={}", currentUser.getName(), authorizationHeader, productId);
        URI uri = loadBalancer.choose("productcomposite").getUri();
        String url = uri.toString() + "/product/" + productId;
        LOG.debug("GetProductComposite from URL: {}", url);

        ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);
        LOG.info("GetProductComposite http-status: {}", result.getStatusCode());
        LOG.debug("GetProductComposite body: {}", result.getBody());

        return result;
    }
}