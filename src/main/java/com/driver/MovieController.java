package com.driver;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class MovieController {
    @Autowired
    MovieService movieservice;
    @PostMapping("/movies/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie){
        boolean m=movieservice.addMovie(movie);
        if(!m){
            return new ResponseEntity<>("It doesn't exit", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Success",HttpStatus.FOUND);
    }
    @PostMapping("/movies/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director){
        boolean d=movieservice.addDirector(director);
        if(!d){
            return new ResponseEntity<>("It doesn't exit", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Success",HttpStatus.FOUND);
    }
    @PutMapping("/movies/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("mn") String moviename,@RequestParam("dn") String directorname){
        boolean a=movieservice.addMovieDirectorPair(moviename,directorname);
        if(!a){
            return new ResponseEntity<>("cant add pair,either movie or director name is invalid", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Successfully added movie director pair",HttpStatus.ACCEPTED);
    }
    @GetMapping("/movies/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name") String name){
        Movie m=movieservice.getMovieByName(name);
        return new ResponseEntity<>(m,HttpStatus.OK);
    }
    @GetMapping("/movies/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable("name") String name){
        Director d=movieservice.getDirectorByName(name);
        return new ResponseEntity<>(d,HttpStatus.OK);
    }
    @GetMapping("/movies/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>>  getMoviesByDirectorName(@PathVariable("director") String name){
        List<String> moviecarrier=movieservice.getMoviesByDirectorName(name);
        return new ResponseEntity<>(moviecarrier,HttpStatus.OK);
    }
    @GetMapping("/movies/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies(){
        List<String> list=movieservice.findAllMovies();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
    @DeleteMapping("/movies/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam("dn") String name){
        movieservice.deleteDirectorByName(name);
        return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);
    }
    @DeleteMapping("/movies/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){
        movieservice.deleteAllDirectors();
        return new ResponseEntity<>("All directors are deleted",HttpStatus.OK);
    }
}

