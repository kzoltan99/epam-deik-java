package ticketservice.repository.impl;

import com.epam.training.ticketservice.dataaccess.dao.RoomDao;
import com.epam.training.ticketservice.dataaccess.model.Room;
import com.epam.training.ticketservice.repository.impl.JpaRoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JpaRoomRepositoryTest {

    private JpaRoomRepository underTest;

    @Mock
    private RoomDao roomDao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new JpaRoomRepository(roomDao);
    }

    @Test
    public void testCreateRoomShouldCreateARoom() {
        //Given
        Room room = new Room("Name", 5, 10);

        //When
        underTest.createRoom(room);

        //Then
        verify(roomDao).save(room);
    }

    @Test
    public void testUpdateRoomShouldUpdateTheCorrectRoom() {
        //Given
        Room room = new Room("Name", 5, 10);
        Room updatedRoom = new Room("Name", 8, 8);

        //When
        when(roomDao.findByName(room.getName())).thenReturn(Optional.of(room));
        underTest.updateRoom(room.getName(), updatedRoom);

        //Then
        verify(roomDao).save(updatedRoom);
    }

    @Test
    public void testDeleteRoomShouldDeleteTheCorrectRoom() {
        //Given
        Room room = new Room("Name", 5, 10);

        //When
        when(roomDao.findByName(room.getName())).thenReturn(Optional.of(room));
        underTest.deleteRoom(room.getName());

        //Then
        verify(roomDao).delete(room);
    }

    @Test
    public void testGetRoomsShouldListAllExistRooms() {
        //When
        underTest.getRooms();

        //Then
        verify(roomDao).findAll();
    }

}
