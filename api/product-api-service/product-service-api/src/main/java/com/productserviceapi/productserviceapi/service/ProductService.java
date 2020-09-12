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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@EnableResourceServer
public class ProductService {
    

    private static final Logger LOG = LoggerFactory.getLogger(ProductService.class);

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private LoadBalancerClient loadBalancer;

    @RequestMapping("/movies/{urlId}")
    @PreAuthorize("hasAnyRole('user','admin')")
    public ResponseEntity<String> getProductComposite(@PathVariable String urlId,
                                    @RequestHeader(value="Authorization") String authorizationHeader,
                                    Principal currentUser) {

        //Debug
        LOG.info("ProductApi: User={}, Auth={}, called with urlId={}", currentUser.getName(), authorizationHeader, urlId);
        
        URI uri = loadBalancer.choose("movie-catalog-service").getUri();
        String url = uri.toString() + "/catalog/" + urlId;

        System.out.println(url);
        // http://localhost:8081/catalog/showmovies
        // http://localhost:8081/catalog/showratedmovies/101
        
        //Debug
        LOG.debug("GetProductComposite from URL: {}", url);

        ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);
        
        //Debug 
        //LOG.info("GetProductComposite http-status: {}", result.getStatusCode());
        //LOG.debug("GetProductComposite body: {}", result.getBody());

        return result;
    }

    @GetMapping("/userratings/{urlId}")
    @PreAuthorize("hasAnyRole('user','admin')")
    public ResponseEntity<String> getRatingComposite(@PathVariable String urlId,
        @RequestHeader(value="Authorization") String authorizationHeader,
        Principal currentUser) {

        //Debug
        LOG.info("ProductApi: User={}, Auth={}, called with urlId={}", currentUser.getName(), authorizationHeader, urlId);

        URI uri = loadBalancer.choose("movie-catalog-service").getUri();
        String url = uri.toString() + "/catalog/showratedmovie/" + urlId;

        System.out.println(url);
        // http://localhost:8081/catalog/showmovies
        // http://localhost:8081/catalog/showratedmovie/101

        //Debug
        LOG.debug("GetProductComposite from URL: {}", url);

        ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);

        //Debug 
        //LOG.info("GetProductComposite http-status: {}", result.getStatusCode());
        //LOG.debug("GetProductComposite body: {}", result.getBody());

        return result;
    }

    // add rating(user,admin), add movies(admin), delete movies(admin), delete ratings(user,admin)

    @PostMapping("/addratings/{urlId}") //working
    @PreAuthorize("hasAnyRole('user','admin')")
    public ResponseEntity<String> PostRatingComposite(@PathVariable String urlId,
        @RequestBody final String rating,
        @RequestHeader(value="Authorization") String authorizationHeader,
        Principal currentUser) {

        //Debug
        LOG.info("ProductApi: User={}, Auth={}, called with urlId={}", currentUser.getName(), authorizationHeader, urlId);

        URI uri = loadBalancer.choose("movie-catalog-service").getUri();
        String url = uri.toString() + "/catalog/" + urlId;

        System.out.println(url);
        // http://localhost:8081/catalog/ratemovies
        

        //Debug
        LOG.debug("GetProductComposite from URL: {}", url);

        ResponseEntity<String> result = restTemplate.postForEntity(url, rating, String.class);

        //Debug 
        //LOG.info("GetProductComposite http-status: {}", result.getStatusCode());
        //LOG.debug("GetProductComposite body: {}", result.getBody());

        return result;
    }

    @PostMapping("/addmovies/{urlId}")
    @PreAuthorize("hasRole('admin')") 
    public ResponseEntity<String> PostMovieComposite(@PathVariable String urlId,
        @RequestBody final String movie,
        @RequestHeader(value="Authorization") String authorizationHeader,
        Principal currentUser) {

        //Debug
        LOG.info("ProductApi: User={}, Auth={}, called with urlId={}", currentUser.getName(), authorizationHeader, urlId);

        URI uri = loadBalancer.choose("movie-catalog-service").getUri();
        String url = uri.toString() + "/catalog/" + urlId;

        System.out.println(url);
        // http://localhost:8081/catalog/addmovies
        

        //Debug
        LOG.debug("GetProductComposite from URL: {}", url);

        ResponseEntity<String> result = restTemplate.postForEntity(url, movie, String.class);

        //Debug 
        //LOG.info("GetProductComposite http-status: {}", result.getStatusCode());
        //LOG.debug("GetProductComposite body: {}", result.getBody());

        return result;
    }

}