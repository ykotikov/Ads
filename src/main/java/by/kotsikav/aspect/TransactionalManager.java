package by.kotsikav.aspect;

import by.kotsikav.connection.DBConnectionManager;
import by.kotsikav.ThreadContext;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by yura5 on 03.04.2016.
 */
public class TransactionalManager implements Aspect {
    @Override
    public boolean isApplied(Object target, Method method, Object[] args) {

        return method.isAnnotationPresent(Transactional.class);
    }

    @Override
    public void before(Object target, Method method, Object[] args) throws SQLException {
        Connection connection = DBConnectionManager.getConnection();
        connection.setAutoCommit(false);
        ThreadContext.setConnection(connection);
    }

    @Override
    public void after(Object target, Method method, Object[] args) throws SQLException {
        ThreadContext.getCurrentConnection().commit();
        ThreadContext.getCurrentConnection().close();
        ThreadContext.removeCurrentConnection();
    }

    @Override
    public void exception(Object target, Method method, Object[] args) throws SQLException {
        ThreadContext.getCurrentConnection().rollback();
        ThreadContext.getCurrentConnection().close();
        ThreadContext.removeCurrentConnection();

    }
}
