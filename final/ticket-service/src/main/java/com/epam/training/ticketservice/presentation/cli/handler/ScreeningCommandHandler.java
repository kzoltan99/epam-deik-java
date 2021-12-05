package com.epam.training.ticketservice.presentation.cli.handler;

import com.epam.training.ticketservice.presentation.cli.UserCommandAvailability;
import com.epam.training.ticketservice.service.ScreeningService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.text.ParseException;

@ShellComponent
public class ScreeningCommandHandler extends UserCommandAvailability {

    private ScreeningService screeningService;

    public ScreeningCommandHandler(ScreeningService screeningService) {
        this.screeningService = screeningService;
    }


    @ShellMethod(value = "Create a screening", key = "create screening")
    @ShellMethodAvailability("isUserSignedIn")
    public String createScreening(String title, String room, String startTime) throws ParseException {
        return screeningService.createScreening(title, room, startTime);
    }

    @ShellMethod(value = "Delete a screening", key = "delete screening")
    @ShellMethodAvailability("isUserSignedIn")
    public void deleteScreening(String title, String room, String startTime) throws ParseException {
        screeningService.deleteScreening(title, room, startTime);
    }

    @ShellMethod(value = "List all screening", key = "list screenings")
    public String listScreenings() {
        return screeningService.listScreenings();
    }

}
