package com.moviecatalog.moviecatalogservice.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.moviecatalog.moviecatalogservice.model.Movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ResolvableType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
// import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
// import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
// import org.springframework.security.oauth2.client.registration.ClientRegistration;
// import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// import com.moviecatalog.moviecatalogservice.model.*;

@RestController
@RequestMapping("/catalog")
public class HomeController {

    // @Bean
    // @LoadBalanced
    // public RestTemplate restTemplate() {
    //     return new RestTemplate();
    // }
    @Autowired
    RestTemplate restTemplate;

    // Map<String, String> oauth2AuthenticationUrls = new HashMap<>();

  
    // @Autowired
    // private OAuth2AuthorizedClientService authorizedClientService;

    // @GetMapping("/loginSuccess")
    // public String getLoginInfo(Model model, OAuth2AuthenticationToken authentication) {
    //     OAuth2AuthorizedClient client = authorizedClientService.loadAuthorizedClient(authentication.getAuthorizedClientRegistrationId(), authentication.getName());

    //     String userInfoEndpointUri = client.getClientRegistration()
    //         .getProviderDetails()
    //         .getUserInfoEndpoint()
    //         .getUri();

    //     if (!StringUtils.isEmpty(userInfoEndpointUri)) {
    //         RestTemplate restTemplate = new RestTemplate();
    //         HttpHeaders headers = new HttpHeaders();
    //         headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + client.getAccessToken()
    //             .getTokenValue());

    //         HttpEntity<String> entity = new HttpEntity<String>("", headers);

    //         ResponseEntity<Map> response = restTemplate.exchange(userInfoEndpointUri, HttpMethod.GET, entity, Map.class);
    //         Map userAttributes = response.getBody();
    //         model.addAttribute("name", userAttributes.get("name"));
    //     }

    //     return "loginSuccess";
    // }

   // @PreAuthorize("hasAuthority('SCOPE_profile')")
    @GetMapping("/showmovies") //<-- product-composite
    //@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public Object[] showAllMovies(){

        ResponseEntity<Movie[]> responseEntity = restTemplate.getForEntity("http://movie-service-api/movieservice/allmovies", Movie[].class);
        Movie[] movies = responseEntity.getBody();
        return movies;
    }

    @GetMapping("/showratedmovie/{userId}")
    //@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public Object[] showAllRatedMovies(@PathVariable String userId){
        ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity("http://review-service-api/ratingservice/allratings/" + userId, Object[].class);
        Object[] objects = responseEntity.getBody();
        return objects;
    }

    @PostMapping("/ratemovie")
    //@PreAuthorize("hasRole('user')")
    public ResponseEntity<String> rateMovies(@RequestBody final String rating) {
         HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
         HttpEntity<String> entity = new HttpEntity<String>(rating, headers);
         ResponseEntity<String> result = restTemplate
                .postForEntity("http://review-service-api/ratingservice/addrating", entity, String.class);
        
        return new ResponseEntity<>(result.getStatusCode()); //working on it
    }

    @PostMapping("/addmovie")
    //@PreAuthorize("hasRole('admin')")
    public ResponseEntity<String> addMovie(@RequestBody final String movie) {
         HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
         HttpEntity<String> entity = new HttpEntity<String>(movie, headers);
         ResponseEntity<String> result = restTemplate
                .postForEntity("http://movie-service-api/movieservice/addmovie", entity, String.class);
        
        return new ResponseEntity<>(result.getStatusCode());
    }
    
    
}