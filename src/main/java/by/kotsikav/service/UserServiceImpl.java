package by.kotsikav.service;

import by.kotsikav.aspect.Transactional;
import by.kotsikav.dao.UserDAO;
import by.kotsikav.dao.UserDAOImpl;
import by.kotsikav.entity.User;

import java.util.List;

/**
 * Created by yura5 on 03.04.2016.
 */
public class UserServiceImpl implements UserService{
    private UserDAO userDAO;
    public UserServiceImpl() {

        userDAO = new UserDAOImpl();
    }

    @Override
    @Transactional
    public User getUserByLogin(String login)  {
        User user = new User();
        return userDAO.getUserByLogin(login);
    }

    @Override
    @Transactional
    public User getUserById(int id)  {
        return userDAO.getUserById(id);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    @Transactional
    public void save(User user)  {
        userDAO.save(user);
    }
}
