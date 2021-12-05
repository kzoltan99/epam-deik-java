package ticketservice.repository.impl;

import com.epam.training.ticketservice.dataaccess.dao.ScreeningDao;
import com.epam.training.ticketservice.dataaccess.model.Screening;
import com.epam.training.ticketservice.repository.impl.JpaScreeningRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Date;

import static org.mockito.Mockito.verify;

public class JpaScreeningRepositoryTest {
    private JpaScreeningRepository underTest;

    @Mock
    private ScreeningDao screeningDao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new JpaScreeningRepository(screeningDao);
    }

    @Test
    public void testCreateScreeningShouldCreateAScreening() {
        //Given
        Date date = Mockito.mock(Date.class);
        Screening screening = new Screening("Title", "Name", date);

        //When
        underTest.createScreening(screening);

        //Then
        verify(screeningDao).save(screening);
    }

    @Test
    public void testListScreeningsShouldListAllExistScreenings() {
        //When
        underTest.getScreenings();

        //Then
        verify(screeningDao).findAll();
    }
}
