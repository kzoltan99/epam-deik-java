package ticketservice.presentation.cli.handler;

import com.epam.training.ticketservice.dataaccess.model.Screening;
import com.epam.training.ticketservice.presentation.cli.handler.ScreeningCommandHandler;
import com.epam.training.ticketservice.service.ScreeningService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.text.ParseException;

import static org.mockito.Mockito.verify;

public class ScreeningCommandHandlerTest {

    private ScreeningCommandHandler underTest;

    @Mock
    private ScreeningService screeningService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new ScreeningCommandHandler(screeningService);
    }

    @Test
    public void testCreateScreeningShouldCreateAScreening() throws ParseException {
        //Given
        Date date = Mockito.mock(Date.class);
        Screening screening = new Screening("Title", "Name", date);

        //When
        underTest.createScreening(screening.getTitle(), screening.getRoomName(), String.valueOf(screening.getStartTime()));

        //Then
        verify(screeningService).createScreening(screening.getTitle(), screening.getRoomName(), String.valueOf(screening.getStartTime()));
    }

    @Test
    public void testDeleteScreeningShouldDeleteTheCorrectScreening() throws ParseException {
        //Given
        Date date = Mockito.mock(Date.class);
        Screening screening = new Screening("Title", "Name", date);

        //When
        underTest.deleteScreening(screening.getTitle(), screening.getRoomName(), String.valueOf(screening.getStartTime()));

        //Then
        verify(screeningService).deleteScreening(screening.getTitle(), screening.getRoomName(), String.valueOf(screening.getStartTime()));
    }

    @Test
    public void testListScreeningsShouldReturnListOfAllExistScreenings() {
        //When
        underTest.listScreenings();

        //Then
        verify(screeningService).listScreenings();
    }
}
