package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.dataaccess.exceptions.PasswordNotMatchException;
import com.epam.training.ticketservice.dataaccess.exceptions.UserNotFoundException;
import com.epam.training.ticketservice.dataaccess.model.User;
import com.epam.training.ticketservice.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserService {

    private UserRepository userRepository;

    private User loggedInUser;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        loggedInUser = null;
    }

    public void login(String name, String password) throws UserNotFoundException, PasswordNotMatchException {
        User user = userRepository.getUserByName(name);
        if (Objects.isNull(user)) {
            throw new UserNotFoundException();
        }

        if (!user.getPassword().equals(password)) {
            throw new PasswordNotMatchException();
        }

        loggedInUser = user;
    }

    public User getLoggedInUser() {
        return this.loggedInUser;
    }

    public boolean isUserLoggedIn() {
        return !Objects.isNull(this.loggedInUser);
    }

    public void logout() {
        this.loggedInUser = null;
    }
}
