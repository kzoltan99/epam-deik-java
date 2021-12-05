package ticketservice.presentation.cli.handler;

import com.epam.training.ticketservice.dataaccess.model.Movie;
import com.epam.training.ticketservice.presentation.cli.handler.MovieCommandHandler;
import com.epam.training.ticketservice.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class MovieCommandHandlerTest {

    private MovieCommandHandler underTest;

    @Mock
    private MovieService movieService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new MovieCommandHandler(movieService);
    }

    @Test
    public void testCreateMovieShouldCreateAMovie() {
        //Given
        Movie movie = new Movie("Title", "test", 120);

        //When
        underTest.createMovie(movie.getTitle(),movie.getGenre(),movie.getLength());

        //Then
        verify(movieService).createMovie(movie.getTitle(),
                movie.getGenre(),movie.getLength());

    }

    @Test
    public void testUpdateMovieShouldUpdateTheCorrectMovie(){
        //Given
        Movie movie = new Movie("Title", "test", 120);
        Movie updatedMovie = new Movie("Title", "drama", 140);

        //When
        underTest.updateMovie(movie.getTitle(),
                updatedMovie.getGenre(),updatedMovie.getLength());

        //Then
        verify(movieService).updateMovie(movie.getTitle(),
                updatedMovie.getGenre(),updatedMovie.getLength());
    }

    @Test
    public void testDeleteMovieShouldDeleteTheCorrectMovie() {
        //Given
        Movie movie = new Movie("Title", "test", 120);

        //When
        underTest.deleteMovie(movie.getTitle());

        //Then
        verify(movieService).deleteMovie(movie.getTitle());
    }

    @Test
    public void testListMoviesShouldReturnListOfAllExistMovie(){
        //When
        underTest.listMovies();

        //Then
        verify(movieService).listAllMovies();
    }

}
