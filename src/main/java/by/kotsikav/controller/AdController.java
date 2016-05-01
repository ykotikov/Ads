package by.kotsikav.controller;

import by.kotsikav.SecurityContextHolder;
import by.kotsikav.aspect.TransactionalManager;
import by.kotsikav.entity.Ad;
import by.kotsikav.entity.Paging;
import by.kotsikav.entity.User;
import by.kotsikav.service.AdService;
import by.kotsikav.service.AdServiceImpl;
import by.kotsikav.service.UserService;
import by.kotsikav.service.UserServiceImpl;
import by.kotsikav.utils.InvocationHandler;
import by.kotsikav.validator.LoginValidator;
import by.kotsikav.validator.Validator;

import javax.ws.rs.HttpMethod;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yura5 on 07.04.2016.
 */
@Controller
public class AdController {
    AdService adService;
    Validator validator;
    UserService userService;
    public AdController() {
        validator = new LoginValidator();
        adService = (AdService) Proxy.newProxyInstance(AdService.class.getClassLoader(),
                AdServiceImpl.class.getInterfaces(),
                new InvocationHandler(new AdServiceImpl(), new TransactionalManager()));
        userService = (UserService) Proxy.newProxyInstance(UserService.class.getClassLoader(),
                UserServiceImpl.class.getInterfaces(),
                new InvocationHandler(new UserServiceImpl(), new TransactionalManager()));
    }

    @RequestMapping(path = "/advertising", method = HttpMethod.GET)
    public ViewModel getAdvertisingPage(@QueryParam final Paging paging) {
        ViewModel model = new ViewModel();
        final List<Ad> ads = adService.getAllAds(paging);
        final List<User> users = userService.getAllUsers();
        final int pageCounts = (int) Math.ceil((1.0 * paging.getRecordSize())/paging.getPageSize());
        paging.setPagesCount(pageCounts);
        model.setModel(new HashMap<String, Object>() {
            {
                put("paging",paging);
                put("ads",ads);
                put("users",users);
            }
        });
        model.setForward("forward:/home.jsp");
        return model;
    }

    @RequestMapping(path = "/user/profile/topics", method = HttpMethod.GET)
    public ViewModel getTopicPage() {
        ViewModel model = new ViewModel();
        User user = SecurityContextHolder.getLoggedUser();
        if(user == null){
            model.setForward("redirect:/advertising");
        }else{
            final List<Ad> ads = adService.getAdsByUserId(user.getId());
            System.out.println(user.getId());
            model.setModel(new HashMap<String, Object>(){
                {
                    put("ads",ads);
                }
            });
            model.setForward("forward:/user-topics.jsp");
        }
        return model;
    }
    @RequestMapping(path = "user/profile/topics/delete/(\\d+)",method = HttpMethod.POST)
    public ViewModel deletePage(@PathParam(0) Integer adId){
        ViewModel model = new ViewModel();
        return model;

    }
    @RequestMapping(path = "advertising/(\\d+)",method = HttpMethod.GET)
    public ViewModel getCurrentAd(@PathParam(0) Integer id){
        ViewModel viewModel = new ViewModel();
        final Ad ad = adService.getAdById(id);
        viewModel.setModel(new HashMap<String, Object>(){
            {
                put("ad",ad);
            }
        });
        viewModel.setForward("forward:/ad.jsp");
        return viewModel;
    }
    @RequestMapping(path = "/user/profile/topics/delete/(\\d+)",method = HttpMethod.GET)
    public ViewModel deleteTopicPage(@PathParam(0) Integer topicId){
        ViewModel model = new ViewModel();
        adService.delete(topicId);
        model.setForward("redirect:/user/profile/topics/");
        return model;
    }
    @RequestMapping(path = "/advertising/create",method = HttpMethod.GET)
    public ViewModel getcreateAdPage(){
        ViewModel model = new ViewModel();
        model.setForward("forward:/create-ad.jsp");
        return model;
    }

    @RequestMapping(path = "/advertising/create",method = HttpMethod.POST)
    public ViewModel createAd(@Body final Ad ad){
        ViewModel model = new ViewModel();
        User user = SecurityContextHolder.getLoggedUser();
        ad.setAuthor(user);
        adService.save(ad);
        model.setForward("redirect:/advertising");
        return model;

    }


}
