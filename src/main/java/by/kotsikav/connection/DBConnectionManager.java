package by.kotsikav.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by yura5 on 03.04.2016.
 */
public class DBConnectionManager {


    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();//TODO:DBConnection
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/adlist","root","кщще");
        return connection;
    }
}
