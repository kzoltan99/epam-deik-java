package com.epam.training.ticketservice.presentation.cli.handler;

import com.epam.training.ticketservice.presentation.cli.UserCommandAvailability;
import com.epam.training.ticketservice.service.MovieService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

@ShellComponent
public class MovieCommandHandler extends UserCommandAvailability {

    private MovieService movieService;


    public MovieCommandHandler(MovieService movieService) {
        this.movieService = movieService;
    }

    @ShellMethod(value = "Create a movie", key = "create movie")
    @ShellMethodAvailability("isUserSignedIn")
    public void createMovie(String title, String genre, int length) {
        movieService.createMovie(title, genre, length);
    }

    @ShellMethod(value = "Update a movie", key = "update movie")
    @ShellMethodAvailability("isUserSignedIn")
    public void updateMovie(String title, String genre, int length) {
        movieService.updateMovie(title, genre, length);
    }

    @ShellMethod(value = "Delete a movie", key = "delete movie")
    @ShellMethodAvailability("isUserSignedIn")
    public void deleteMovie(String title) {
        movieService.deleteMovie(title);
    }

    @ShellMethod(value = "List movies", key = "list movies")
    public String listMovies() {
        return movieService.listAllMovies();
    }
}
