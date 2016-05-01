package by.kotsikav.service;

import by.kotsikav.dao.AdDAO;
import by.kotsikav.dao.AdDAOImpl;
import by.kotsikav.dao.UserDAO;
import by.kotsikav.dao.UserDAOImpl;
import by.kotsikav.entity.Ad;
import by.kotsikav.entity.Paging;
import by.kotsikav.entity.User;

import java.util.List;

/**
 * Created by yura5 on 07.04.2016.
 */
public class AdServiceImpl implements AdService {
    AdDAO adDAO;
    UserDAO userDAO;

    public AdServiceImpl() {
        adDAO = new AdDAOImpl();
        userDAO = new UserDAOImpl();
    }

    @Override
    public Ad getAdById(Integer id) {
        Ad ad = new Ad();
        User user = new User();
        ad = adDAO.getAdById(id);
        ad.setAuthor(userDAO.getUserById(ad.getAuthorId()));
        return ad;
    }

    @Override
    public List<Ad> getAdsByUserId(Integer userId) {
        List<Ad> list = adDAO.getAdsByUserId(userId);
        System.out.println("ADService"+list.toString());
        User user = userDAO.getUserById(userId);
        for (int i = 0; i < list.size(); i++) {
            Ad ad = list.get(i);
            int id = ad.getAuthorId();
            ad.setAuthor(user);
            list.set(i, ad);
        }

        return list;
    }

    @Override
    public List<Ad> getAllAds(Paging paging) {
        List<Ad> list = adDAO.getAllAds(paging);
        List<User> users = userDAO.getAllUsers();
        for (int i = 0; i < list.size(); i++) {
            Ad ad = list.get(i);
            for (int j = 0; j < users.size(); j++) {
                int id = ad.getAuthorId();
                if (id == users.get(j).getId()) {
                    ad.setAuthor(users.get(j));
                }
            }
            list.set(i, ad);
        }

        return list;
    }

    @Override
    public void save(Ad ad) {
        User user = ad.getAuthor();
        ad.setAuthorId(user.getId());
        adDAO.save(ad);
    }

    @Override
    public void delete(Integer id) {
        adDAO.delete(id);
    }
}
