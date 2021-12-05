package ticketservice.presentation.cli.handler;

import com.epam.training.ticketservice.dataaccess.model.User;
import com.epam.training.ticketservice.presentation.cli.handler.UserCommandHandler;
import com.epam.training.ticketservice.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserCommandHandlerTest {

    @InjectMocks
    private UserCommandHandler underTest;

    @Mock
    private UserService userService;

    @Test
    public void testSignInPrivilegedShouldSignInPrivileged() {
        // Given
        String username = "test";
        String password = "test";

        //When
        String actual = underTest.signInPrivileged(username,password);

        //Then
        Assertions.assertEquals("", actual);
    }

    @Test
    public void testDescribeAccountShouldReturnNotSignedInTextWhenNotSignedIn(){
        //When
        when(userService.isUserLoggedIn()).thenReturn(false);
        String description = underTest.describeAccount();

        //Then
        Assertions.assertEquals("You are not signed in",description);
    }

    @Test
    public void testDescribeAccountShouldReturnADescriptionWhenSignedIn(){
        //Given
        User user = new User("admin", "admin");

        //When
        when(userService.isUserLoggedIn()).thenReturn(true);
        when(userService.getLoggedInUser()).thenReturn(user);
        String description = underTest.describeAccount();

        //Then
        Assertions.assertEquals("Signed in with privileged account '"
                + user.getName() + "'",description);
    }

    @Test
    public void logOutShouldLogUserOut(){
        //When
        String actual = underTest.signOut();

        //Then
        Assertions.assertEquals("Signing out",actual);

    }
}
