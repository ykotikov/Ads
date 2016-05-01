package by.kotsikav.utils;

import by.kotsikav.controller.RequestMapping;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * Created by yura5 on 03.04.2016.
 */
public class ScanPackageUtil {
    private static Set<Class<?>> classes;
    public static RegexMap getAnnotationPath(String path, Class<? extends Annotation> annotation){
        Reflections reflections = new Reflections(path);
        classes = reflections.getTypesAnnotatedWith(annotation);
        RegexMap regexMap = new RegexMap();
        for(Class controller : classes){
            Method[] methods = controller.getDeclaredMethods();
            for(Method method : methods){
                RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                if(requestMapping!=null){
                    regexMap.put(requestMapping.path()+"@" + requestMapping.method(),method);
                }
            }
        }
        return regexMap;
    }
}
