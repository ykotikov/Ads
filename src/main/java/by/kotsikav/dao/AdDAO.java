package by.kotsikav.dao;

import by.kotsikav.entity.Ad;
import by.kotsikav.entity.Paging;

import java.util.List;

/**
 * Created by yura5 on 03.04.2016.
 */
public interface AdDAO {
    Ad getAdById(Integer id) ;

    List<Ad> getAdsByUserId(Integer userId) ;

    List<Ad> getAllAds(Paging paging) ;

    void save(Ad ad) ;

    void delete(Integer id);
}
