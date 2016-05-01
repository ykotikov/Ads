package by.kotsikav.servlet;

import by.kotsikav.SecurityContextHolder;
import by.kotsikav.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by yura5 on 10.04.2016.
 */
@WebFilter(urlPatterns = {"/user/login",
                            "user/register"}
)
public class AuthentificationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession(true);
        User loggedUser = SecurityContextHolder.getLoggedUser();
        session.setAttribute("userSession", loggedUser);

    }

    @Override
    public void destroy() {

    }
}
