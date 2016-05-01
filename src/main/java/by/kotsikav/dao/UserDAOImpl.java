package by.kotsikav.dao;

import by.kotsikav.ThreadContext;
import by.kotsikav.dao.util.DAOUtil;
import by.kotsikav.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yura5 on 03.04.2016.
 */
public class UserDAOImpl extends AbstractDAO implements UserDAO {
    @Override
    public User getUserByLogin(String login) {
        String query = "SELECT * FROM USER WHERE LOGIN = ?";
        ResultSet rs = executeQuery(query, login);
        return getUser(rs);
    }


    @Override
    public User getUserById(int id)  {
        String query = "SELECT * FROM USER WHERE ID = ?";
        ResultSet rs = executeQuery(query, id);
        return getUser(rs);

    }

    @Override
    public List<User> getAllUsers() {
        String query = "SELECT * FROM adlist.user";
        ResultSet rs = execute(query);
        return getUsers(rs);

    }

   private List<User> getUsers(ResultSet rs) {
        List<User> users = new ArrayList<>();
        if(rs!=null){
            try {
                while(rs.next()){
                    User user = new User();
                    user.setId(rs.getInt("ID"));
                    user.setLogin(rs.getString("LOGIN"));
                    user.setPassword(rs.getString("PASSWORD"));
                    user.setEmail(rs.getString("EMAIL"));
                    users.add(user);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    private ResultSet execute(String query) {
        Connection connection = ThreadContext.getCurrentConnection();
        ResultSet rs = null;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            rs = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }


    @Override
    public void save(User user)  {
        String query = "INSERT INTO user(LOGIN,PASSWORD,EMAIL) VALUES(?,?,?)";
        executeUpdate(query,user.getLogin(),user.getPassword(),user.getEmail());
}

    private User getUser(ResultSet rs) {
        User user = new User();
        try {
            rs.next();
            user.setId(rs.getInt("ID"));
            user.setLogin(rs.getString("LOGIN"));
            user.setPassword(rs.getString("PASSWORD"));
            user.setEmail(rs.getString("EMAIL"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DAOUtil.cleanupResources(rs);
        }
        return user;
    }
}
