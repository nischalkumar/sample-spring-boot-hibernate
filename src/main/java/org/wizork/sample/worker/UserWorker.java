package org.wizork.sample.worker;

import org.springframework.beans.factory.annotation.Autowired;
import org.wizork.sample.common.Enums.Role;
import org.wizork.sample.domain.User;
import org.wizork.sample.jpa.UserDao;

import java.util.Set;

/**
 * Created by hari_om on 6/14/15.
 */
public class UserWorker {
    @Autowired
    private UserDao userDao;

    public UserWorker() {}

    public User getUser(String username) {
        return userDao.findByLogin(username);
    }

    public User insertUser(User user, Set<Role> roleSet) {
        return userDao.save(user, roleSet);
    }
}
