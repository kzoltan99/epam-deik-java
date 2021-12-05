package com.epam.training.ticketservice.presentation.cli.handler;

import com.epam.training.ticketservice.presentation.cli.UserCommandAvailability;
import com.epam.training.ticketservice.service.RoomService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

@ShellComponent
public class RoomCommandHandler extends UserCommandAvailability {

    private RoomService roomService;

    public RoomCommandHandler(RoomService roomService) {
        this.roomService = roomService;
    }

    @ShellMethod(value = "Create a room", key = "create room")
    @ShellMethodAvailability("isUserSignedIn")
    public void createRoom(String name, int rows, int columns) {
        roomService.createRoom(name, rows, columns);
    }

    @ShellMethod(value = "Update a room", key = "update room")
    @ShellMethodAvailability("isUserSignedIn")
    public void updateRoom(String name, int rows, int columns) {
        roomService.updateRoom(name, rows, columns);
    }

    @ShellMethod(value = "Delete a room", key = "delete room")
    @ShellMethodAvailability("isUserSignedIn")
    public void deleteRoom(String name) {
        roomService.deleteRoom(name);
    }

    @ShellMethod(value = "List rooms", key = "list rooms")
    public String listRooms() {
        return roomService.listAllRooms();
    }
}
