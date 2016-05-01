package by.kotsikav.service;

import by.kotsikav.aspect.Transactional;
import by.kotsikav.entity.Ad;
import by.kotsikav.entity.Paging;

import java.util.List;

/**
 * Created by yura5 on 07.04.2016.
 */
public interface AdService {
    @Transactional
    Ad getAdById(Integer id) ;
    @Transactional
    List<Ad> getAdsByUserId(Integer userId) ;
    @Transactional
    List<Ad> getAllAds(Paging paging) ;
    @Transactional
    void save(Ad ad) ;
    @Transactional
    void delete(Integer id);
}
