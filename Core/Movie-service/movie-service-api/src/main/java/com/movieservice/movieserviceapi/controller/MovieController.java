package com.movieservice.movieserviceapi.controller;

import com.movieservice.movieserviceapi.service.MovieService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import com.movieservice.movieserviceapi.model.*;
@RestController
@RequestMapping("/movieservice")
public class MovieController {
    
    @Autowired
    MovieService movieService;

    @GetMapping("/allmovies")
    //@HystrixCommand(fallbackMethod = "getFallbackAllmovies")
    public List<Movie> getAllMovies(){
        return this.movieService.getallMovies();
    }
    
    // public List<Movie> getFallbackAllmovies(){
    //     List<Movie> ls = new ArrayList();
    //     return ls;
    // }

    @GetMapping("/movie/{movieId}")
    //@HystrixCommand(fallbackMethod = "getFallbackMovieByName")
    public Movie getMovieByName(@PathVariable String movieId){
        return this.movieService.getMovie(Long.parseLong(movieId));
    }
    // public Movie getFallbackMovieByName(@PathVariable String movieId){
    //     Movie mv = new Movie(0,"No Movie","No description");
    //     return mv;
    // }

    //admin
    @PostMapping("/addmovie")
    //@HystrixCommand(fallbackMethod = "addFallbackMovie")
    public Movie addMovie(@RequestBody Movie movie){
        return this.movieService.addMovie(movie);
    }
    // public Movie addFallbackMovie(@RequestBody Movie movie){
    //     Movie mv = new Movie(0,"No Movie","No description");
    //     return mv;
    // }

    @PutMapping("/updatemovie/{movieId}")
    //@HystrixCommand(fallbackMethod = "addFallbackUpdate")
    public Movie addOrUpdateMovie(@RequestBody Movie movie, @PathVariable String movieId){
        Movie mv = movieService.getMovie(Long.parseLong(movieId));
        return this.movieService.addOrUpdateMovie(movie);
    }
    // public Movie addFallbackUpdate(@RequestBody Movie movie, @PathVariable String movieId){
    //     Movie mv = new Movie(0,"No Movie","No description");
    //     return mv;
    // }

    @DeleteMapping("/deleteMovie/{movieId}")
    //@HystrixCommand(fallbackMethod = "DeletetFallbackRating")
    public ResponseEntity<HttpStatus> deleteRating(@PathVariable String movieId){
        try{
            this.movieService.deleteMovie(Long.parseLong(movieId));
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // public ResponseEntity<HttpStatus> DeletetFallbackRating(@PathVariable String movieId){
        
    //     return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    // }
}