package by.kotsikav.validator;

import by.kotsikav.aspect.TransactionalManager;
import by.kotsikav.service.UserService;
import by.kotsikav.service.UserServiceImpl;
import by.kotsikav.utils.InvocationHandler;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * Created by yura5 on 03.04.2016.
 */

public class LoginValidator implements Filter,Validator {
    private UserService userService;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        userService = (UserService) Proxy.newProxyInstance(UserService.class.getClassLoader(),
                                                UserServiceImpl.class.getInterfaces(),
                                                new InvocationHandler(new UserServiceImpl(),new TransactionalManager()));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
      //  HashMap<String,ChatUser> activeUsers= (HashMap<Strnig, ChatUser>) req.getServletContext().getAttribute("activeUsers");
        String previousSessionId = null;
        String name = null;
        if(request.getCookies()!=null){
            for (Cookie aCookie : request.getCookies()) {
                if (aCookie.getName().equals("sessionId")) {
                    previousSessionId = aCookie.getValue();
                    break;
                }
            }
        }
        System.out.println("Filter");
/*
        if (previousSessionId != null && activeUsers!=null) {
            for (ChatUser aUser : activeUsers.values()) {
                if (aUser.getSessionId().equals(previousSessionId)) {
                    name = aUser.getName();
                    aUser.setSessionId(req.getSession().getId());
                    req.getSession().setAttribute("name",name);
                    req.getRequestDispatcher("chat.jsp").forward(request,response);
                }
            }
        }*/
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }

    @Override
    public List<String> validate(Object obj) {
        return null;
    }
}
