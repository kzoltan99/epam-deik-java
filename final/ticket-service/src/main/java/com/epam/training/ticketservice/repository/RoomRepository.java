package com.epam.training.ticketservice.repository;

import com.epam.training.ticketservice.dataaccess.model.Room;

import java.util.List;

public interface RoomRepository {
    void createRoom(Room room);

    void updateRoom(String name, Room room);

    void deleteRoom(String name);

    List<Room> getRooms();
}
