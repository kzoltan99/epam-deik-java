package com.epam.training.ticketservice.dataaccess.init;

import com.epam.training.ticketservice.dataaccess.dao.UserDao;
import com.epam.training.ticketservice.dataaccess.model.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserDatabaseInit {

    private final UserDao userDao;

    public UserDatabaseInit(UserDao userDao) {
        this.userDao = userDao;
    }

    @PostConstruct
    public void initAdmin() {
        userDao.save(new User("admin", "admin"));
    }
}
