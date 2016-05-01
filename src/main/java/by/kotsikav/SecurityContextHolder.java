package by.kotsikav;

import by.kotsikav.entity.User;

/**
 * Created by yura5 on 10.04.2016.
 */
public class SecurityContextHolder {
    private static final ThreadLocal<User> securityContext = new ThreadLocal<>();

    public static User getLoggedUser() {
        return securityContext.get();
    }

    public static void setLoggedUser(User user) {
        securityContext.set(user);
    }

    public static void logoutUser() {
        securityContext.remove();
    }
}
