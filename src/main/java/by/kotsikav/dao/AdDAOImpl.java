package by.kotsikav.dao;

import by.kotsikav.ThreadContext;
import by.kotsikav.controller.QueryParam;
import by.kotsikav.dao.util.DAOUtil;
import by.kotsikav.entity.Ad;
import by.kotsikav.entity.Paging;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yura5 on 03.04.2016.
 */
public class AdDAOImpl extends AbstractDAO implements AdDAO {
    @Override
    public Ad getAdById(Integer id) {
        String query = "SELECT * FROM ad WHERE ID = ?";
        ResultSet rs = executeQuery(query, id);
        return getAd(rs);
    }

    @Override
    public List<Ad> getAdsByUserId(Integer userId) {
        String query = "SELECT " +
                " * from ad WHERE authorId = ?";
        List<Ad> ads = execute(query, null, userId);
       // System.out.println("ADS"+ads);
        return ads;
    }


    @Override
    public List<Ad> getAllAds(@QueryParam Paging paging) {
        String query = "SELECT SQL_CALC_FOUND_ROWS" +
                " * from ad LIMIT ?,?";

        return execute(query, paging, (paging.getPage() - 1) * paging.getPageSize(), paging.getPageSize());

    }

    @Override
    public void save(Ad ad) {
        String query = "INSERT INTO ad(authorId,BODY,SUBJECT) VALUES(?,?,?)";
        executeUpdate(query, ad.getAuthorId(), ad.getBody(), ad.getSubject());
    }

    @Override
    public void delete(Integer id) {
        String query = "DELETE FROM ad WHERE id = ?";
        executeUpdate(query, id);
    }

    private List<Ad> execute(String query, Paging paging, Object... params) {
        Connection connection = ThreadContext.getCurrentConnection();
        List<Ad> ads = new ArrayList<>();
        ResultSet rs = null;
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            rs = ps.executeQuery();
            ads = getAds(rs);
           // System.out.println("DAO+"+ads);
            if(paging!=null) {
                rs = ps.executeQuery("SELECT FOUND_ROWS()");
                if (rs.next()) {
                    paging.setRecordSize(rs.getInt(1));
                }
            }

            return ads;


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DAOUtil.cleanupResources(rs);
        }

        return new ArrayList<>();
    }

    private List<Ad> getAds(ResultSet rs) {
        List<Ad> ads = new ArrayList<>();
        try {
            while (rs.next()) {
                Ad ad = new Ad();
                ad.setId(rs.getInt("ID"));
                ad.setAuthorId(rs.getInt("authorId"));
                ad.setBody(rs.getString("BODY"));
                ad.setSubject(rs.getString("SUBJECT"));
                ads.add(ad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DAOUtil.cleanupResources(rs);
        }
        return ads;
    }

    private Ad getAd(ResultSet rs) {
        Ad ad = new Ad();
        try {
            if (rs.next()) {
                ad.setId(rs.getInt("ID"));
                ad.setAuthorId(rs.getInt("authorId"));
                ad.setBody(rs.getString("BODY"));
                ad.setSubject(rs.getString("SUBJECT"));
                return ad;
            }

        } catch (SQLException e) {
        } finally {
            DAOUtil.cleanupResources(rs);
        }
        return null;
    }
}
