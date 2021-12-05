package com.epam.training.ticketservice.repository.impl;

import com.epam.training.ticketservice.dataaccess.dao.RoomDao;
import com.epam.training.ticketservice.dataaccess.model.Room;
import com.epam.training.ticketservice.repository.RoomRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JpaRoomRepository implements RoomRepository {

    RoomDao roomDao;

    public JpaRoomRepository(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @Override
    public void createRoom(Room room) {
        roomDao.save(new Room(room.getName(), room.getRows(), room.getColumns()));
    }

    @Override
    public void updateRoom(String name, Room room) {
        Room roomProjection = (Room) roomDao.findByName(name).orElseThrow(() ->
                new IllegalArgumentException("Can't find room with " + name + " name.")
        );

        roomProjection.setRows(room.getRows());
        roomProjection.setColumns(room.getColumns());
        roomDao.save(roomProjection);
    }

    @Override
    public void deleteRoom(String name) {
        Room room = (Room) roomDao.findByName(name).orElseThrow(() ->
                new IllegalArgumentException("Can't find room with " + name + " name.")
        );

        roomDao.delete(room);
    }

    @Override
    public List<Room> getRooms() {
        List<Room> rooms = roomDao.findAll();
        return mapRooms(rooms);
    }

    private List<Room> mapRooms(List<Room> rooms) {
        return rooms.stream()
                .map(this::mapRoom)
                .collect(Collectors.toList());
    }

    private Room mapRoom(Room room) {
        return new Room(room.getName(), room.getRows(), room.getColumns());
    }
}
