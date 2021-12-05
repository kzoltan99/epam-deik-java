package ticketservice.service;

import com.epam.training.ticketservice.dataaccess.model.Movie;
import com.epam.training.ticketservice.repository.MovieRepository;
import com.epam.training.ticketservice.service.MovieService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    private MovieService underTest;

    @Mock
    private MovieRepository movieRepository;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new MovieService(movieRepository);
    }

    @Test
    public void testCreateMovieShouldCreateAMovie() {
        //Given
        Movie movie = new Movie("Title", "test", 120);

        //When
        underTest.createMovie(movie.getTitle(), movie.getGenre(), movie.getLength());

        //Then
        verify(movieRepository).createMovie(movie);
    }

    @Test
    public void testUpdateMovieShouldUpdateTheCorrectMovie() {
        //Given
        Movie updatedMovie = new Movie("Title", "drama", 140);

        //When
        underTest.updateMovie(updatedMovie.getTitle(),
                updatedMovie.getGenre(),updatedMovie.getLength());

        //Then
        verify(movieRepository).updateMovie(updatedMovie.getTitle(), updatedMovie);
    }

    @Test
    public void testListAllMoviesIfMoviesEmptyShouldSayNoMoviesAtNow() {
        //When
        when(movieRepository.getMovies()).thenReturn(Arrays.asList());

        //Then
        Assertions.assertEquals("There are no movies at the moment", underTest.listAllMovies());
        verify(movieRepository).getMovies();
    }

    @Test
    public void testListAllMoviesShouldListExistMovies() {
        //Given
        Movie movie = new Movie("Title", "test", 120);

        //When
        when(movieRepository.getMovies()).thenReturn(Arrays.asList(movie));

        //Then
        Assertions.assertEquals("Title (test, 120 minutes)", underTest.listAllMovies());
    }

    @Test
    public void testDeleteMovieShouldDeleteTheCorrectMovie() {
        //Given
        String movie = "Title";

        //When
        underTest.deleteMovie(movie);

        //Then
        verify(movieRepository).deleteMovie(movie);
    }
}
