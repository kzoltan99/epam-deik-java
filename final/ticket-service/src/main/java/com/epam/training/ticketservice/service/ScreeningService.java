package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.dataaccess.model.Movie;
import com.epam.training.ticketservice.dataaccess.model.Screening;
import com.epam.training.ticketservice.repository.MovieRepository;
import com.epam.training.ticketservice.repository.ScreeningRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ScreeningService {

    private ScreeningRepository screeningRepository;
    private MovieRepository movieRepository;

    public ScreeningService(ScreeningRepository screeningRepository, MovieRepository movieRepository) {
        this.screeningRepository = screeningRepository;
        this.movieRepository = movieRepository;
    }

    public String createScreening(String title, String room, String startTime) throws ParseException {
        Screening screening = new Screening(title, room, stringToDate(startTime));
        if (isOverlapping(room, startTime, 0)) {
            return "There is an overlapping screening";
        } else if (isOverlappingBreak(room, startTime)) {
            return "This would start in the break period after another screening in this room";
        } else {
            screeningRepository.createScreening(screening);
            return "";
        }
    }

    private boolean isOverlappingBreak(String room, String startTime) throws ParseException {
        return isOverlapping(room, startTime, 10);
    }

    private boolean isOverlapping(String room, String startTime, int plusMin) throws ParseException {
        List<Screening> screenings = screeningRepository.getScreenings();
        for (Screening s : screenings) {
            Date startMovie = s.getStartTime();
            Date endMovie = new Date(startMovie.getTime()
                    + (movieRepository.getMovieByTitle(s.getTitle()).getLength()) * (60000L)
                    + plusMin * (60000L));

            Date time = stringToDate(startTime);
            if (time.getTime() >= startMovie.getTime()
                && time.getTime() < endMovie.getTime()
                && s.getRoomName().equals(room)) {
                return true;
            }
        }
        return false;
    }

    public Date stringToDate(String time) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return simpleDateFormat.parse(time);
    }

    public String dateToString(Date time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return simpleDateFormat.format(time);
    }

    public String listScreenings() {
        List<Screening> screenings = screeningRepository.getScreenings();
        StringBuilder allScreenings = new StringBuilder();
        if (screenings.isEmpty()) {
            allScreenings.append("There are no screenings");
        } else {
            for (Screening s : screenings) {
                Movie m = movieRepository.getMovieByTitle(s.getTitle());
                allScreenings.append(m.toString());
                allScreenings.setLength(allScreenings.length() - 1);
                allScreenings.append(s.toString())
                        .append(dateToString(s.getStartTime()))
                        .append("\n");
            }
            allScreenings.setLength(allScreenings.length() - 1);
        }
        return allScreenings.toString();
    }

    public void deleteScreening(String title, String room, String startTime) throws ParseException {
        screeningRepository.deleteByTitleAndRoomAndStartTime(title, room, startTime);
    }
}
