package by.kotsikav.aspect;

import java.lang.reflect.Method;
import java.sql.SQLException;

/**
 * Created by yura5 on 03.04.2016.
 */
public interface Aspect {
    boolean isApplied(Object target, Method method, Object[] args);

    void before(Object target, Method method, Object[] args) throws SQLException;

    void after(Object target, Method method, Object[] args) throws SQLException;

    void exception(Object target, Method method, Object[] args) throws SQLException;
}
