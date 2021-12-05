package ticketservice.dataaccess.model;

import com.epam.training.ticketservice.dataaccess.model.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class MovieTest {

    @Test
    public void testGetTitle() {
        //Given
        Movie movie = new Movie("Test title", "test", 120);

        //Then
        Assertions.assertEquals("Test title", movie.getTitle());

    }

    @Test
    public void testSetAndGetGenre() {
        //Given
        Movie movie = new Movie("Test title", "test", 120);

        //Then
        Assertions.assertEquals("test", movie.getGenre());

        //When
        movie.setGenre("test genre");

        //Then
        Assertions.assertEquals("test genre", movie.getGenre());

    }

    @Test
    public void testSetAndGetLength() {
        //Given
        Movie movie = new Movie("Test title", "test", 120);

        //Then
        Assertions.assertEquals(120, movie.getLength());

        //When
        movie.setLength(140);

        //Then
        Assertions.assertEquals(140, movie.getLength());

    }

    @Test
    public void testToStringReturnWithCorrectString() {
        //Given
        Movie movie = new Movie("Test title", "test", 120);

        //Then
        Assertions.assertEquals("Test title (test, 120 minutes)\n", movie.toString());
    }
}
