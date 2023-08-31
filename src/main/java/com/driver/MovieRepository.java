package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class MovieRepository {
    HashMap<String,Movie> moviedb=new HashMap<>();
    HashMap<String,Director> directordb=new HashMap<>();
    HashMap<String,List<String>> moviedirectorpair=new HashMap<>();

    public boolean addMovie(Movie movie) {
        if(!moviedb.isEmpty()||!moviedb.containsKey(movie.getName())){
            moviedb.put(movie.getName(), movie);
            return true;
        }
        return false;
    }

    public boolean addDirector(Director director) {
        if(directordb.isEmpty()||!directordb.containsKey(director.getName())){
           directordb.put(director.getName(), director);
            return true;
        }
        return false;
    }

    public boolean addMovieDirectorPair(String moviename, String directorname) {
        if(moviedb.containsKey(moviename)&&directordb.containsKey(directorname)){
            if(moviedirectorpair.containsKey(directorname)){
                moviedirectorpair.get(directorname).add(moviename);
                return true;
            }
            List<String> list=new ArrayList<>();
            list.add(moviename);
            moviedirectorpair.put(directorname,list);
            return true;
        }
        return false;
    }

    public Movie getMovieByName(String name) {
        if(moviedb.containsKey(name)){
            return moviedb.get(name);
        }
        return null;
    }

    public Director getDirectorByName(String name) {
        if(directordb.containsKey(name)){
            return directordb.get(name);
        }
        return null;
    }

    public List<String> getMoviesByDirectorName(String name) {
        if(moviedirectorpair.containsKey(name)){
            return moviedirectorpair.get(name);
        }
        return new ArrayList<>();
    }

    public List<String> findAllMovies() {
        if(moviedb.isEmpty()){
            return new ArrayList<>();
        }
        return new ArrayList<>(moviedb.keySet());
    }

    public void deleteDirectorByName(String name) {
        if(moviedirectorpair.containsKey(name)){
            for(String s:moviedirectorpair.get(name)){
                moviedb.remove(s);
            }
            moviedirectorpair.remove(name);
        }
        if(directordb.containsKey(name)){
            directordb.remove(name);
        }
    }

    public void deleteAllDirectors() {
        for(String name :moviedirectorpair.keySet()){
            for(String s:moviedirectorpair.get(name)) {
                moviedb.remove(s);
            }
            moviedirectorpair.remove(name);
        }
        for(String name:directordb.keySet()){
            directordb.remove(name);
        }
    }
}
