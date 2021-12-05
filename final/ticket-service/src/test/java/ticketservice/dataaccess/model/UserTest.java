package ticketservice.dataaccess.model;

import com.epam.training.ticketservice.dataaccess.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    public void testGetName() {
        //Given
        User user = new User("Test name", "test password");

        //Then
        Assertions.assertEquals("Test name", user.getName());

    }
}
