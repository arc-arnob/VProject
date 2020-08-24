package com.moviecatalog.moviecatalogservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import com.moviecatalog.moviecatalogservice.api.Resources.Facebook;
import com.moviecatalog.moviecatalogservice.model.*;

@RestController
@RequestMapping("/catalog")
public class HomeController {
    
    @Autowired
    RestTemplate restTemplate;

    Facebook facebook;
    
    @Autowired
    public HomeController(Facebook facebook){
        this.facebook = facebook;
    }


    @GetMapping("/showmovies")
    public Object[] showAllMovies(){
        
        ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity("http://localhost:9090/movieservice/allmovies", Object[].class);
        Object[] objects = responseEntity.getBody();
        return objects;
    }

    @GetMapping("/showratedmovie/{userId}")
    public Object[] showAllRatedMovies(@PathVariable String userId){
        ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity("http://localhost:9091/ratingservice/allratings/" + userId, Object[].class);
        Object[] objects = responseEntity.getBody();
        return objects;
    }
    
    
}