package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.dataaccess.model.Movie;
import com.epam.training.ticketservice.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void createMovie(String title, String genre, int length) {
        movieRepository.createMovie(new Movie(title, genre, length));
    }

    public void updateMovie(String title, String genre, int length) {
        Movie movie = new Movie(title, genre, length);
        movieRepository.updateMovie(title, movie);
    }

    public String listAllMovies() {
        List<Movie> movies = movieRepository.getMovies();
        StringBuilder allMovies = new StringBuilder();
        if (movies.isEmpty()) {
            allMovies.append("There are no movies at the moment");
        } else {
            for (Movie m : movies) {
                allMovies.append(m.toString());
            }
            allMovies.setLength(allMovies.length() - 1);
        }
        return allMovies.toString();
    }

    public void deleteMovie(String title) {
        movieRepository.deleteMovie(title);
    }
}
