package by.kotsikav.servlet;

import by.kotsikav.BuilderParameterImpl;
import by.kotsikav.controller.Controller;
import by.kotsikav.controller.RequestMapping;
import by.kotsikav.controller.model.ViewModel;
import by.kotsikav.utils.RegexMap;
import by.kotsikav.utils.ScanPackageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yura5 on 03.04.2016.
 */
@WebServlet(urlPatterns = "/")
public class DispatcherServlet extends HttpServlet {
    private RegexMap map;

    @Override
    public void init() throws ServletException {
        map = ScanPackageUtil.getAnnotationPath("by.kotsikav.controller", Controller.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            execute(req, resp);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            execute(req, resp);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void execute(HttpServletRequest request, HttpServletResponse response) throws InstantiationException, IllegalAccessException, InvocationTargetException, IOException, ServletException {
        StringBuffer path = getPath(request);
        System.out.println(path);
        Method method = (Method) map.get(path);
       // System.out.println("METHOD" + method.getName());
        Map<String, List<ParameterValue>> params = null;
        List<String> matches = null;
        ViewModel model = new ViewModel();
        if (method != null) {
            matches = getMatcher(method,path,request);
            params = getRequestParams(request);
            Object[] parametres = new BuilderParameterImpl().build(request, method, response, matches,params);
            model = getViewModel(method, parametres);
            if (model != null) {
                forward(model, request, response);
            }
        }

    }

    private List<String> getMatcher(Method method, StringBuffer path, HttpServletRequest request) {
        String match = null;
        List<String> matches = new ArrayList<>();
        if(method != null){
            if(request.getQueryString()==null){
                String methodPath = method.getAnnotation(RequestMapping.class).path();
                Pattern pattern = Pattern.compile(methodPath);
                Matcher matcher = pattern.matcher(path);
                matcher.find();
                for (int i = 1; i <= matcher.groupCount(); i++) {
                    String group = matcher.group(i);
                    matches.add(group);
                }
                return matches;

            }
        }
        return new ArrayList<>();
    }


    private void forward(ViewModel model, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Map<String, Object> viewModelMap = new HashMap<>();
        viewModelMap = model.getModel();
        if (viewModelMap != null) {
            for (String str : viewModelMap.keySet()) {
               // System.out.println(str +"+++"+viewModelMap.get(str));
                request.setAttribute(str, viewModelMap.get(str));
            }
        }
        String redirectPage = model.getForward();
        if (redirectPage.startsWith("redirect:")) {
            redirectPage = redirectPage.substring(9);
            response.sendRedirect(redirectPage);
        } else if (redirectPage.startsWith("forward:")) {
            redirectPage = redirectPage.substring(8);
            request.getRequestDispatcher(redirectPage).forward(request, response);
        }
    }

    private ViewModel getViewModel(Method method, Object... parameters) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<?> clazz = method.getDeclaringClass();
        Object obj = clazz.newInstance();
        return invoke(method, obj, parameters);
    }

    private ViewModel invoke(Method method, Object obj, Object[] parameters) throws InvocationTargetException, IllegalAccessException {
        ViewModel viewModel = null;
        if (method.getReturnType().equals(ViewModel.class)) {
            viewModel = (ViewModel) method.invoke(obj, parameters);
        } else {
            method.invoke(obj);
        }
        return viewModel;

    }


    private Map<String, List<ParameterValue>> getRequestParams(HttpServletRequest request) {
        Map<String, List<ParameterValue>> parameters = new HashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            ArrayList<ParameterValue> values = new ArrayList<>();
            for (String value : request.getParameterValues(paramName)) {
                values.add(new ParameterValue(value));
            }
            parameters.put(paramName, values);
        }
        return parameters;
    }

    private StringBuffer getPath(HttpServletRequest request) {
        StringBuffer buffer = new StringBuffer(request.getRequestURI());
        buffer.append("@");
        buffer.append(request.getMethod().toUpperCase());
        return buffer;
    }
}
