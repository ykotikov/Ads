package by.kotsikav.servlet;

import by.kotsikav.SecurityContextHolder;
import by.kotsikav.entity.User;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by yura5 on 10.04.2016.
 */
@WebListener
public class AuthentificationPropagationListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {


    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        HttpServletRequest request = (HttpServletRequest) servletRequestEvent.getServletRequest();
        HttpSession session = request.getSession(false);
        //System.out.println("AuthorRequest");

        if(session == null){
            return;
        }
        User user = (User) session.getAttribute("userSession");
        SecurityContextHolder.setLoggedUser(user);
    }
}
