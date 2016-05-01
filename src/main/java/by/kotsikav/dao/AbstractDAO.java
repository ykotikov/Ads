package by.kotsikav.dao;

import by.kotsikav.ThreadContext;
import by.kotsikav.dao.util.DAOUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public  abstract class AbstractDAO {
    protected Long executeUpdate(String query, Object... params)  {
        Connection connection = ThreadContext.getCurrentConnection();
        ResultSet rs = null;
        try (PreparedStatement ps = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()){
                return rs.getLong(1);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DAOUtil.cleanupResources(rs);
        }
        return null;

    }

    protected ResultSet executeQuery(String query, Object... params)  {
        Connection connection = ThreadContext.getCurrentConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            return ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
