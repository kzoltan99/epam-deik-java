package ticketservice.repository.impl;

import com.epam.training.ticketservice.dataaccess.dao.MovieDao;
import com.epam.training.ticketservice.dataaccess.model.Movie;
import com.epam.training.ticketservice.repository.impl.JpaMovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JpaMovieRepositoryTest {

    @InjectMocks
    private JpaMovieRepository underTest;

    @Mock
    private MovieDao movieDao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new JpaMovieRepository(movieDao);
    }

    @Test
    public void testCreateMovieShouldCreateAMovie() {
        //Given
        Movie movie = new Movie("Title", "test", 120);

        //When
        underTest.createMovie(movie);

        //Then
        verify(movieDao).save(movie);
    }

    @Test
    public void testUpdateMovieShouldUpdateTheCorrectMovie() {
        //Given
        Movie movie = new Movie("Title", "test", 120);
        Movie updatedMovie = new Movie("Title", "drama", 140);

        //When
        when(movieDao.findByTitle(movie.getTitle())).thenReturn(Optional.of(movie));
        underTest.updateMovie(movie.getTitle(), updatedMovie);

        //Then
        verify(movieDao).save(updatedMovie);
    }

    @Test
    public void testDeleteMovieShouldDeleteTheCorrectMovie() {
        //Given
        Movie movie = new Movie("Title", "test", 120);

        //When
        when(movieDao.findByTitle(movie.getTitle())).thenReturn(Optional.of(movie));
        underTest.deleteMovie(movie.getTitle());

        //Then
        verify(movieDao).delete(movie);
    }

    @Test
    public void testGetMoviesShouldGetAllExistMovies() {
        //When
        underTest.getMovies();

        //Then
        verify(movieDao).findAll();
    }

}
