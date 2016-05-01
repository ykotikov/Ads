package by.kotsikav.service;

import by.kotsikav.aspect.Transactional;
import by.kotsikav.entity.User;

import java.util.List;

/**
 * Created by yura5 on 03.04.2016.
 */
public interface UserService {
    @Transactional
    User getUserByLogin(String login) ;

    @Transactional
    User getUserById(int id) ;

    @Transactional
    List<User> getAllUsers();

    @Transactional
    void save(User user) ;
}
