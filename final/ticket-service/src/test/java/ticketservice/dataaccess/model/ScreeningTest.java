package ticketservice.dataaccess.model;

import com.epam.training.ticketservice.dataaccess.model.Screening;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Date;
import java.text.ParseException;

public class ScreeningTest {

    @Test
    public void testGetTitle() throws ParseException {
        //Given
        Date date = Mockito.mock(Date.class);
        Screening screening = new Screening("Test title", "Test room", date);

        //Then
        Assertions.assertEquals("Test title", screening.getTitle());
    }

    @Test
    public void testGetRoomName() throws ParseException {
        //Given
        Date date = Mockito.mock(Date.class);
        Screening screening = new Screening("Test title", "Test room", date);

        //Then
        Assertions.assertEquals("Test room", screening.getRoomName());
    }

    @Test
    public void testGetStartTime() throws ParseException {
        //Given
        Date date = Mockito.mock(Date.class);
        Screening screening = new Screening("Test title", "Test room", date);

        //Then
        Assertions.assertEquals(date, screening.getStartTime());
    }

    @Test
    public void testToStringReturnWithCorrectString() {
        //Given
        Date date = Mockito.mock(Date.class);
        Screening screening= new Screening("Title", "Name", date);

        //Then
        Assertions.assertEquals(", screened in room Name, at ", screening.toString());
    }
}
