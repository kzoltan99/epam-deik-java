package ticketservice.service;

import com.epam.training.ticketservice.dataaccess.model.Screening;
import com.epam.training.ticketservice.repository.MovieRepository;
import com.epam.training.ticketservice.repository.ScreeningRepository;
import com.epam.training.ticketservice.service.ScreeningService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.text.ParseException;
import java.util.Arrays;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ScreeningServiceTest {

    private ScreeningService underTest;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private ScreeningRepository screeningRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new ScreeningService(screeningRepository, movieRepository);
    }

    @Test
    public void testCreateScreeningShouldCreateAScreening() throws ParseException {
        //Given
        Date date = Mockito.mock(Date.class);
        Screening screening = new Screening("Title", "Name", underTest.stringToDate("2021-05-12 17:00"));

        //When
        underTest.createScreening(screening.getTitle(), screening.getRoomName(), underTest.dateToString(screening.getStartTime()));

        //Then
        verify(screeningRepository).createScreening(screening);
    }

    @Test
    public void testListScreeningsShouldSayNoScreeningAtNow() {
        //When
        when(screeningRepository.getScreenings()).thenReturn(Arrays.asList());

        //Then
        Assertions.assertEquals("There are no screenings", underTest.listScreenings());
        verify(screeningRepository).getScreenings();
    }

    @Test
    public void testDeleteScreeningShouldDeleteTheCorrectScreening() throws ParseException {
        //Given
        Screening screening = new Screening("Title", "Name", underTest.stringToDate("2021-05-12 17:00"));

        //When
        underTest.deleteScreening(screening.getTitle(), screening.getRoomName(), underTest.dateToString(screening.getStartTime()));

        //Then
        verify(screeningRepository).deleteByTitleAndRoomAndStartTime(screening.getTitle(),
                screening.getRoomName(), underTest.dateToString(screening.getStartTime()));
    }
}
