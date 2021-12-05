package ticketservice.dataaccess.model;

import com.epam.training.ticketservice.dataaccess.model.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RoomTest {

    @Test
    public void testGetName() {
        //Given
        Room room = new Room("Test name", 5, 10);

        //Then
        Assertions.assertEquals("Test name", room.getName());

    }

    @Test
    public void testSetAndGetGenre() {
        //Given
        Room room = new Room("Test name", 5, 10);

        //Then
        Assertions.assertEquals(5, room.getRows());

        //When
        room.setRows(8);

        //Then
        Assertions.assertEquals(8, room.getRows());

    }

    @Test
    public void testSetAndGetLength() {
        //Given
        Room room = new Room("Test name", 5, 10);

        //Then
        Assertions.assertEquals(10, room.getColumns());

        //When
        room.setColumns(8);

        //Then
        Assertions.assertEquals(8, room.getColumns());

    }

    @Test
    public void testToStringReturnWithCorrectString() {
        //Given
        Room room = new Room("Test name", 5, 10);

        //Then
        Assertions.assertEquals("Room Test name with 50 seats, 5 rows and 10 columns\n", room.toString());
    }
}
