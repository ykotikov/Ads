package by.kotsikav.controller;

import by.kotsikav.SecurityContextHolder;
import by.kotsikav.aspect.TransactionalManager;
import by.kotsikav.entity.User;
import by.kotsikav.service.UserService;
import by.kotsikav.service.UserServiceImpl;
import by.kotsikav.utils.InvocationHandler;
import by.kotsikav.validator.UserValidator;
import by.kotsikav.validator.Validator;
import org.apache.commons.collections4.CollectionUtils;

import javax.ws.rs.HttpMethod;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yura5 on 03.04.2016.
 */
@Controller
public class UserController {
    UserService userService;
    Validator validator;

    public UserController() {
        validator = new UserValidator();

        userService = (UserService) Proxy.newProxyInstance(UserService.class.getClassLoader(),
                UserServiceImpl.class.getInterfaces(),
                new InvocationHandler(new UserServiceImpl(), new TransactionalManager()));
    }

    @RequestMapping(path = "/user/logout", method = HttpMethod.GET)
    public ViewModel logout() {
        SecurityContextHolder.logoutUser();
        ViewModel model = new ViewModel();
        model.setForward("redirect:/advertising");
        return model;
    }

    @RequestMapping(path = "/user/login", method = HttpMethod.POST)
    public ViewModel login(@Body final User user) {
        User us = userService.getUserByLogin(user.getLogin());
        ViewModel model = new ViewModel();
        final List<String> errors = new ArrayList<>();
        System.out.println(us.toString());
        if(us.getId() != 0){
            SecurityContextHolder.setLoggedUser(us);
            model.setForward("redirect:/advertising");
        }
        else{
            errors.add("Wrong Login or Password");
            model.setModel(new HashMap<String, Object>(){
                {
                    put("errors",errors);
                }
            });
            model.setForward("forward:/login.jsp");
        }
        return model;
    }
    @RequestMapping(path = "/user/login", method = HttpMethod.GET)
    public ViewModel getLoginPage() {
        ViewModel model = new ViewModel();
        model.setForward("forward:/login.jsp");
        return model;
    }

    @RequestMapping(path = "user/register", method = HttpMethod.GET)
    public ViewModel getRegisterPage() {
        ViewModel viewModel = new ViewModel();
        viewModel.setForward("forward:/register.jsp");
        return viewModel;
    }

    @RequestMapping(path = "user/register", method = HttpMethod.POST)
    public ViewModel register(@Body final User user) {
        final List<String> errors = validator.validate(user);
        final List<String> loginError = new ArrayList<>();
        ViewModel viewModel = new ViewModel();
        User ur = userService.getUserByLogin(user.getLogin());
        if ( ur.getLogin()!= null || ur.getEmail()!=null) {//TODO:ошибка в сравнении если login без mail то прокатит
            loginError.add("User with this name is register");
            System.out.println(loginError);
            viewModel.setModel(new HashMap<String, Object>(){
                {
                    put("errors",loginError);
                }
            });
            viewModel.setForward("forward:/register.jsp");
            return viewModel;
        }

        if (!CollectionUtils.isEmpty(errors)) {
            viewModel.setModel(new HashMap<String, Object>() {
                {
                    put("errors", errors);
                    put("user", user);
                }
            });
            viewModel.setForward("forward:/register.jsp");
        } else {
            SecurityContextHolder.setLoggedUser(user);
            userService.save(user);
            viewModel.setForward("redirect:/user/profile");
        }
        return viewModel;
    }

    @RequestMapping(path = "user/profile", method = HttpMethod.GET)
    public ViewModel getUserCabinet() {
        ViewModel viewModel = new ViewModel();
        viewModel.setForward("forward:/user-cabinet.jsp");
        return viewModel;
    }

    @RequestMapping(path = "user/profile/(\\d+)", method = HttpMethod.GET)
    public ViewModel getProfileCabinet(@PathParam(0) Integer id) {
        ViewModel model = new ViewModel();
        final User user = userService.getUserById(id);
        model.setModel(new HashMap<String, Object>() {
            {
                put("user", user);
            }
        });
        model.setForward("forward:/profile-user.jsp");
        return model;
    }
}
