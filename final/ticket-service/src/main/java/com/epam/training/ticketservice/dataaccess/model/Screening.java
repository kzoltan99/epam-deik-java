package com.epam.training.ticketservice.dataaccess.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Screening {

    @Id
    @GeneratedValue
    private UUID id;
    private String title;
    private String room;
    private Date startTime;


    public Screening(String title, String room, Date startTime) {
        this.title = title;
        this.room = room;
        this.startTime = startTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Screening screening = (Screening) o;
        return Objects.equals(id, screening.id) && Objects.equals(title, screening.title)
                && Objects.equals(room, screening.room)
                && Objects.equals(startTime, screening.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, room, startTime);
    }

    protected Screening() {
    }

    public String getTitle() {
        return title;
    }

    public String getRoomName() {
        return room;
    }

    public Date getStartTime() {
        return startTime;
    }

    @Override
    public String toString() {
        return ", screened in room " + room + ", at ";
    }
}
