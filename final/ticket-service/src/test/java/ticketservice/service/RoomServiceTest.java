package ticketservice.service;

import com.epam.training.ticketservice.dataaccess.model.Room;
import com.epam.training.ticketservice.repository.RoomRepository;
import com.epam.training.ticketservice.service.RoomService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RoomServiceTest {

    private RoomService underTest;

    @Mock
    private RoomRepository roomRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new RoomService(roomRepository);
    }

    @Test
    public void testCreateRoomShouldCreateARoom() {
        //Given
        Room room = new Room("Name", 5, 10);

        //When
        underTest.createRoom(room.getName(), room.getRows(), room.getColumns());

        //Then
        verify(roomRepository).createRoom(room);
    }

    @Test
    public void testUpdateRoomShouldUpdateTheCorrectRoom() {
        //Given
        Room updatedRoom = new Room("Name", 8, 8);

        //When
        underTest.updateRoom(updatedRoom.getName(),
                updatedRoom.getRows(),updatedRoom.getColumns());

        //Then
        verify(roomRepository).updateRoom(updatedRoom.getName(), updatedRoom);
    }

    @Test
    public void testListAllRoomsIfRoomsEmptyShouldSayNoRoomsAtNow() {
        //When
        when(roomRepository.getRooms()).thenReturn(Arrays.asList());

        //Then
        Assertions.assertEquals("There are no rooms at the moment", underTest.listAllRooms());
        verify(roomRepository).getRooms();
    }

    @Test
    public void testListAllRoomsShouldListExistRooms() {
        //Given
        Room room = new Room("Name", 5, 10);

        //When
        when(roomRepository.getRooms()).thenReturn(Arrays.asList(room));

        //Then
        Assertions.assertEquals("Room Name with 50 seats, 5 rows and 10 columns", underTest.listAllRooms());
    }

    @Test
    public void testDeleteRoomShouldDeleteTheCorrectRoom() {
        //Given
        String room = "teszt";

        //When
        underTest.deleteRoom(room);

        //Then
        verify(roomRepository).deleteRoom(room);
    }
}
