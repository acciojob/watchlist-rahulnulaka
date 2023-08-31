package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service


public class MovieService {

    @Autowired
    MovieRepository movieRepository;
    public boolean addMovie(Movie movie) {
        return movieRepository.addMovie(movie);
    }

    public boolean addDirector(Director director) {
        return movieRepository.addDirector(director);
    }

    public boolean addMovieDirectorPair(String moviename, String directorname) {
        return movieRepository.addMovieDirectorPair(moviename,directorname);
    }

    public Movie getMovieByName(String name) {
        return movieRepository.getMovieByName(name);
    }

    public Director getDirectorByName(String name) {
        return movieRepository.getDirectorByName(name);
    }

    public List<String> getMoviesByDirectorName(String name) {
        return movieRepository.getMoviesByDirectorName(name);
    }

    public List<String> findAllMovies() {
        return movieRepository.findAllMovies();
    }

    public void deleteDirectorByName(String name) {
         movieRepository.deleteDirectorByName(name);
    }

    public void deleteAllDirectors() {
        movieRepository.deleteAllDirectors();
    }
}
