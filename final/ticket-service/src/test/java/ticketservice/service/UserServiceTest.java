package ticketservice.service;

import com.epam.training.ticketservice.dataaccess.exceptions.PasswordNotMatchException;
import com.epam.training.ticketservice.dataaccess.exceptions.UserNotFoundException;
import com.epam.training.ticketservice.dataaccess.model.User;
import com.epam.training.ticketservice.repository.UserRepository;
import com.epam.training.ticketservice.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService underTest;

    @Mock
    private UserRepository userRepository;

    @Test
    public void testLoginShouldLogInTheCorrectUser() throws UserNotFoundException, PasswordNotMatchException {
        // Given
        String username = "test";
        String password = "test";
        User user = new User(username, password);

        // When
        when(userRepository.getUserByName(username)).thenReturn(user);
        underTest.login(username, password);

        // Then
        Assertions.assertEquals(user, underTest.getLoggedInUser());
        verifyNoMoreInteractions(userRepository);
    }
}
