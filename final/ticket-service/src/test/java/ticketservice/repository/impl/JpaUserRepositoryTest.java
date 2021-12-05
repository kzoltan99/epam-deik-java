package ticketservice.repository.impl;

import com.epam.training.ticketservice.dataaccess.dao.UserDao;
import com.epam.training.ticketservice.dataaccess.model.User;
import com.epam.training.ticketservice.repository.impl.JpaUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JpaUserRepositoryTest {

    @InjectMocks
    private JpaUserRepository underTest;

    @Mock
    private UserDao userDao;

    @Test
    public void testGetUserByUsernameShouldGetSingleUserByUserName() {
        //Given
        String username = "test";
        String password = "test";
        User testUser = new User(username, password);
        //When
        when(userDao.findByName(username)).thenReturn(Optional.of(testUser));
        User actualUser = underTest.getUserByName(username);

        //Then
        Assertions.assertEquals(testUser,actualUser);
    }
}
