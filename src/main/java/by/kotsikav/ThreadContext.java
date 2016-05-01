package by.kotsikav;

import java.sql.Connection;

/**
 * Created by yura5 on 03.04.2016.
 */
public class ThreadContext {
    private final static ThreadLocal<Connection> threadContext = new ThreadLocal<>();
    public static Connection getCurrentConnection(){
        return threadContext.get();
    }
    public static void setConnection(Connection connection){
        threadContext.set(connection);
    }
    public static void removeCurrentConnection(){
        threadContext.remove();
    }
}
