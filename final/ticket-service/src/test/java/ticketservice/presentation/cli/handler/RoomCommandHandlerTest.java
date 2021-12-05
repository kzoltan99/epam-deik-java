package ticketservice.presentation.cli.handler;

import com.epam.training.ticketservice.dataaccess.model.Room;
import com.epam.training.ticketservice.presentation.cli.handler.RoomCommandHandler;
import com.epam.training.ticketservice.service.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class RoomCommandHandlerTest {

    private RoomCommandHandler underTest;

    @Mock
    private RoomService roomService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new RoomCommandHandler(roomService);
    }

    @Test
    public void testCreateRoomShouldCreateARoom() {
        //Given
        Room room = new Room("Name", 5, 10);

        //When
        underTest.createRoom(room.getName(), room.getRows(), room.getColumns());

        //Then
        verify(roomService).createRoom(room.getName(), room.getRows(), room.getColumns());

    }

    @Test
    public void testUpdateRoomShouldUpdateTheCorrectRoom(){
        //Given
        Room room = new Room("Name", 5, 10);
        Room updatedRoom = new Room("Name", 8, 8);

        //When
        underTest.updateRoom(room.getName(), updatedRoom.getRows(), updatedRoom.getColumns());

        //Then
        verify(roomService).updateRoom(room.getName(), updatedRoom.getRows(), updatedRoom.getColumns());
    }

    @Test
    public void testDeleteRoomShouldDeleteTheCorrectRoom() {
        //Given
        Room room = new Room("Name", 5, 10);

        //When
        underTest.deleteRoom(room.getName());

        //Then
        verify(roomService).deleteRoom(room.getName());
    }

    @Test
    public void testListRoomsShouldReturnListOfAllExistRooms(){
        //When
        underTest.listRooms();

        //Then
        verify(roomService).listAllRooms();
    }
}
