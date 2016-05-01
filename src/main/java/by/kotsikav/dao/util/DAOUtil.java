package by.kotsikav.dao.util;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yura5 on 03.04.2016.
 */
public class DAOUtil {
    public static void cleanupResources(ResultSet rs){
        if(rs!=null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
