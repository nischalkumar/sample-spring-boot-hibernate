package org.wizork.sample.jpa;

import org.springframework.stereotype.Repository;
import org.wizork.sample.common.Enums.Role;
import org.wizork.sample.domain.User;

import javax.transaction.Transactional;
import java.util.Set;

/**
 * Created by hari_om on 6/16/15.
 */
@Repository(value = "UserDao")
@Transactional
public interface UserDao {
    User findByLogin(String login);
    User findByLoginAndPassword(String login, String password);
    User findById(int userId);
    User save(User User, Set<Role> roleSet);
}
