package com.db.my_spring.configurator;

import com.db.my_spring.ObjectFactory;
import com.db.my_spring.annotation.InjectByType;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PostConstructConfigurator{
    public void configure(Object obj) throws IllegalAccessException, InvocationTargetException {
/*
        Method[] methods = obj.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(PostConstruct.class)) {
                method.setAccessible(true);
                method.invoke(obj);
            }
        }
*/
    }
}
