package com.epam.training.ticketservice.dataaccess.dao;

import com.epam.training.ticketservice.dataaccess.model.Screening;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public interface ScreeningDao extends JpaRepository<Screening, UUID> {
    Optional<Screening> findByTitleAndRoomAndStartTime(String title, String room, Date startTime);
}