package com.epam.training.ticketservice.repository;

import com.epam.training.ticketservice.dataaccess.model.Movie;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository {

    void createMovie(Movie movie);

    void updateMovie(String title, Movie movie);

    void deleteMovie(String title);

    List<Movie> getMovies();

    Movie getMovieByTitle(String title);
}
