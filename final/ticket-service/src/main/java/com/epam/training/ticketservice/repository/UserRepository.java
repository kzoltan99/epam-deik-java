package com.epam.training.ticketservice.repository;

import com.epam.training.ticketservice.dataaccess.model.User;

public interface UserRepository {
    User getUserByName(String name);
}
