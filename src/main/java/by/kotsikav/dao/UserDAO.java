package by.kotsikav.dao;

import by.kotsikav.entity.User;

import java.util.List;

/**
 * Created by yura5 on 03.04.2016.
 */
public interface UserDAO {
    User getUserByLogin(String login) ;

    User getUserById(int id) ;

    List<User> getAllUsers();

    void save(User user) ;
}
