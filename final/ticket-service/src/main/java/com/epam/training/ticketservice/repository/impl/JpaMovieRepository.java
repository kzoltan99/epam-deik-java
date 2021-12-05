package com.epam.training.ticketservice.repository.impl;

import com.epam.training.ticketservice.dataaccess.dao.MovieDao;
import com.epam.training.ticketservice.dataaccess.model.Movie;
import com.epam.training.ticketservice.repository.MovieRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaMovieRepository implements MovieRepository {

    MovieDao movieDao;

    public JpaMovieRepository(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public void createMovie(Movie movie) {
        movieDao.save(new Movie(movie.getTitle(), movie.getGenre(), movie.getLength()));
    }

    @Override
    public void updateMovie(String title, Movie movie) {
        Movie movieProjection = movieDao.findByTitle(title).orElseThrow(() ->
                new IllegalArgumentException("Can't find movie with " + title + " title.")
        );

        movieProjection.setGenre(movie.getGenre());
        movieProjection.setLength(movie.getLength());
        movieDao.save(movieProjection);
    }

    @Override
    public void deleteMovie(String title) {
        Movie movie = movieDao.findByTitle(title).orElseThrow(() ->
                new IllegalArgumentException("Can't find movie with " + title + " title.")
        );

        movieDao.delete(movie);
    }

    @Override
    public List<Movie> getMovies() {
        List<Movie> movies = movieDao.findAll();
        return mapMovies(movies);
    }

    @Override
    public Movie getMovieByTitle(String title) {
        Optional<Movie> movie = movieDao.findByTitle(title);
        if (!movie.isPresent()) {
            return null;
        }
        return movie.get();
    }

    private List<Movie> mapMovies(List<Movie> movies) {
        return movies.stream()
                .map(this::mapMovie)
                .collect(Collectors.toList());
    }

    private Movie mapMovie(Movie movie) {
        return new Movie(movie.getTitle(), movie.getGenre(), movie.getLength());
    }

}
