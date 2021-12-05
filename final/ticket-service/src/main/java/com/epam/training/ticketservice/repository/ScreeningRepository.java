package com.epam.training.ticketservice.repository;

import com.epam.training.ticketservice.dataaccess.model.Screening;

import java.text.ParseException;
import java.util.List;

public interface ScreeningRepository {
    void createScreening(Screening screening);

    List<Screening> getScreenings();

    boolean deleteByTitleAndRoomAndStartTime(String title, String room, String startTime) throws ParseException;
}
