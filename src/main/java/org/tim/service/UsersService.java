package org.tim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tim.dao.UsersDAO;
import org.tim.domain.User;

/**
 * @author tim
 */
@Service
public class UsersService {
    @Autowired
    private UsersDAO usersDAO;

    public void createUser(User user) {
        usersDAO.create(user);
    }

    public void updateUser(User user) {

    }

    public void deleteUser(long userId) {

    }

    public boolean loginUser(String username, String password) {
        return true;
    }
}
