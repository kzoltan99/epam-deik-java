package com.epam.training.ticketservice.repository.impl;

import com.epam.training.ticketservice.dataaccess.dao.ScreeningDao;
import com.epam.training.ticketservice.dataaccess.model.Screening;
import com.epam.training.ticketservice.repository.ScreeningRepository;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaScreeningRepository implements ScreeningRepository {

    private ScreeningDao screeningDao;

    public JpaScreeningRepository(ScreeningDao screeningDao) {
        this.screeningDao = screeningDao;
    }

    @Override
    public void createScreening(Screening screening) {
        screeningDao.save(mapScreening(screening));
    }

    private Screening mapScreening(Screening screening) {
        return new Screening(screening.getTitle(),
                screening.getRoomName(),
                screening.getStartTime());
    }

    public List<Screening> getScreenings() {
        List<Screening> screenings = screeningDao.findAll();
        return mapScreenings(screenings);
    }

    @Override
    public boolean deleteByTitleAndRoomAndStartTime(String title, String room, String startTime) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Optional<Screening> screening = screeningDao
                .findByTitleAndRoomAndStartTime(title, room, format.parse(startTime));
        if (screening.isPresent()) {
            screeningDao.delete(screening.get());
            return true;
        }
        return false;
    }

    private List<Screening> mapScreenings(
            List<Screening> screenings) {
        return screenings.stream()
                .map(this::mapScreening)
                .collect(Collectors.toList());
    }

}
