package by.kotsikav;

import by.kotsikav.servlet.ParameterValue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * Created by yura5 on 05.04.2016.
 */
public interface BuilderParameter {
    Object[] build(HttpServletRequest request, Method method, HttpServletResponse response, List<String> match, Map<String, List<ParameterValue>> parametres) throws InstantiationException, IllegalAccessException, UnsupportedEncodingException;
}
