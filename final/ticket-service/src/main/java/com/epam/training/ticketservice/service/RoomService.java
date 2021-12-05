package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.dataaccess.model.Room;
import com.epam.training.ticketservice.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public void createRoom(String name, int rows, int columns) {
        roomRepository.createRoom(new Room(name, rows, columns));
    }

    public String listAllRooms() {
        List<Room> rooms = roomRepository.getRooms();
        StringBuilder allRooms = new StringBuilder();
        if (rooms.isEmpty()) {
            allRooms.append("There are no rooms at the moment");
        } else {
            for (Room r : rooms) {
                allRooms.append(r.toString());
            }
            allRooms.setLength(allRooms.length() - 1);
        }
        return allRooms.toString();
    }

    public void updateRoom(String name, int rows, int columns) {
        Room room = new Room(name, rows, columns);
        roomRepository.updateRoom(name, room);
    }

    public void deleteRoom(String name) {
        roomRepository.deleteRoom(name);
    }
}
