package by.kotsikav;

import by.kotsikav.controller.Body;
import by.kotsikav.controller.PathParam;
import by.kotsikav.controller.QueryParam;
import by.kotsikav.entity.Overall;
import by.kotsikav.entity.Paging;
import by.kotsikav.servlet.ParameterValue;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yura5 on 05.04.2016.
 */
public class BuilderParameterImpl implements BuilderParameter {
    @Override
    public Object[] build(HttpServletRequest request, Method method, HttpServletResponse response, List<String> match,Map<String, List<ParameterValue>> parametres) throws InstantiationException, IllegalAccessException, UnsupportedEncodingException {
        if (method != null) {
            Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            Object[] parameterValues = new Object[parameterAnnotations.length];

            for (int i = 0; i < parameterAnnotations.length; i++) {
                Class<?> paramType = method.getParameterTypes()[i];
                Body bodyParamAnnotation = (Body) findAnnotation(parameterAnnotations[i], Body.class);
                PathParam pathParamAnnotation = (PathParam) findAnnotation(parameterAnnotations[i], PathParam.class);
                QueryParam queryParamAnnotation = (QueryParam) findAnnotation(parameterAnnotations[i], QueryParam.class);

                if (bodyParamAnnotation != null) {
                    parameterValues[i] = buildBodyParams(paramType, parametres);
                } else if (pathParamAnnotation != null) {
                    parameterValues[i] = buildPathParams(match,paramType, pathParamAnnotation);
                }else if(queryParamAnnotation !=null){
                    parameterValues[i] = buildQueryParameter(paramType,request);
                }
                else if (paramType.isAssignableFrom(HttpServletResponse.class)) {
                    parameterValues[i] = response;
                } else if (paramType.isAssignableFrom(HttpServletRequest.class)) {
                    parameterValues[i] = request;
                }
            }
            return parameterValues;
        }

        return new Object[0];
    }

    private Object buildPathParams(List<String> match,Class<?> paramType, PathParam pathParamAnnotation) {
        int groupNumber = pathParamAnnotation.value();
        String matcher = match.get(groupNumber);
        return convert(paramType,matcher);
    }
    private Object buildBodyParams(Class<?> paramType, Map<String, List<ParameterValue>> parametres) throws IllegalAccessException, InstantiationException {
        Object o = paramType.newInstance();
        Field[] fields = paramType.getDeclaredFields();
        if (fields != null) {
            for (Field field : fields) {
                field.setAccessible(true);
                String key = field.getName();
                List<ParameterValue> value = parametres.get(key);
                if (!CollectionUtils.isEmpty(value) && value.size() == 1) {
                    //System.out.println("Value" + value + " field" + field);
                    field.set(o, value.get(0).getValue());
                }
                field.setAccessible(false);
            }
        }
        return o;
    }

    private Object convert(Class<?> targetType, String text){
        PropertyEditor editor = PropertyEditorManager.findEditor(targetType);
       // editor.setValue(Integer.parseInt(text));
        editor.setAsText(text);
        return editor.getValue();
    }
    private Object buildQueryParameter(Class<?> paramType, HttpServletRequest request) throws IllegalAccessException, InstantiationException {
        Object object = paramType.newInstance();
        if(request.getQueryString() != null ){
            String [] queryString = request.getQueryString().split("&");
            Map<String,String> queryParametres = getQueryParametres(queryString);
            Field[] fields = paramType.getDeclaredFields();
            setQueryParametres(queryParametres,fields,object);
        }
        if(request.getQueryString() == null && object.getClass() == Paging.class){
            Paging paging = new Paging();
            paging.setPage(1);
            object = paging;
        }
        
        return object;
    }

    private void setQueryParametres(Map<String, String> queryParametres, Field[] fields, Object object) throws IllegalAccessException {
        if(!MapUtils.isEmpty(queryParametres)){
            for (Field field: fields){
                field.setAccessible(true);
                Class<?> paramType = field.getType();
                if(field.isAnnotationPresent(Overall.class)){
                    continue;
                }
                String key = field.getName();
                String value = queryParametres.get(key);
                field.set(object,convert(paramType,value));
                field.setAccessible(false);
            }
        }
    }

    private Map<String, String> getQueryParametres(String[] queryParams) {
        Map<String,String> queryParametres = new HashMap<>();
        for(String queryParam:queryParams){
            int indexOf = queryParam.indexOf("=");
            if(indexOf!=-1){
                String key = queryParam.substring(0,indexOf);
                String value = queryParam.substring(indexOf+1);
                queryParametres.put(key,value);
            }
        }
        return queryParametres;
    }


    private Annotation findAnnotation(Annotation[] parameterAnnotations, Class annotationClass) {
        for (Annotation annotation : parameterAnnotations) {
            if (annotation.annotationType().isAssignableFrom(annotationClass)) {
                return annotation;
            }
        }
        return null;
    }
}
