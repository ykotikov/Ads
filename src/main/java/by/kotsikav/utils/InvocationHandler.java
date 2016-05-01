package by.kotsikav.utils;

import by.kotsikav.aspect.Aspect;

import java.lang.reflect.Method;

/**
 * Created by yura5 on 03.04.2016.
 */
public class InvocationHandler implements java.lang.reflect.InvocationHandler {
    private Object target;
    private Aspect aspect;

    public InvocationHandler(Object target, Aspect aspect) {
        this.aspect = aspect;
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result;
        //System.out.println("BEFORE");
        if (isApplied(method, args, aspect, target)) {
            before(method, args, aspect, target);
            try {
                result = method.invoke(target, args);
            } catch (Exception e) {
                throw e;
            }
            after(method, args, aspect, target);
            //System.out.println("AFTER");
        } else {
            result = method.invoke(target, args);
        }
        return result;
    }

    private void after(Method method, Object[] args, Aspect aspect,
                       Object target) throws Exception {
        aspect.after(target, method, args);
    }


    private void before(Method method, Object[] args, Aspect aspect,
                        Object target) throws Exception {
        aspect.before(target, method, args);
    }

    private boolean isApplied(Method method, Object[] args, Aspect aspect, Object target) throws Exception {
        return aspect.isApplied(target, method, args);
    }


}
