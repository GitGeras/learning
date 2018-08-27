package com.db.my_spring;

import com.db.my_spring.configurator.ObjectConfigurator;
import com.db.my_spring.configurator.ProxyConfigurator;
import lombok.SneakyThrows;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;

import javax.annotation.PostConstruct;
import java.lang.reflect.*;
import java.util.*;

public class ObjectFactory {
    private static ObjectFactory ourInstance = new ObjectFactory();
    private ConfigurationUtil config = new ConfigurationUtilImpl();
    private Reflections scanner = new Reflections("com.db.my_spring");
    private Set<ObjectConfigurator> objectConfigurators = new HashSet<>();
    private Set<ProxyConfigurator> proxyConfigurators = new HashSet<>();

    public static ObjectFactory getInstance() {
        return ourInstance;
    }

    @SneakyThrows
    private ObjectFactory() {
        Set<Class<? extends ObjectConfigurator>> fieldConfiguratorClasses = scanner.getSubTypesOf(ObjectConfigurator.class);
        for (Class<? extends ObjectConfigurator> clazz : fieldConfiguratorClasses) {
            if (!Modifier.isAbstract(clazz.getModifiers())) {
                objectConfigurators.add(clazz.newInstance());
            }
        }
        Set<Class<? extends ProxyConfigurator>> methodConfiguratorClasses = scanner.getSubTypesOf(ProxyConfigurator.class);
        for (Class<? extends ProxyConfigurator> clazz : methodConfiguratorClasses) {
            if (!Modifier.isAbstract(clazz.getModifiers())) {
                proxyConfigurators.add(clazz.newInstance());
            }
        }
    }


    @SneakyThrows
    public <T> T createObject(Class<T> type){
        type = resolveImpl(type);

        T t = type.newInstance();

        configureFields(t);

        invokeInitMethods(type, t);

        t = wrapWithProxy(type, t);

        return t;

    }

    private <T> T wrapWithProxy(Class<T> type, T t) throws Exception {
        for (ProxyConfigurator configurator : proxyConfigurators) {
            t = configurator.wrapWithProxy(type, t);
        }
        return t;
    }

    private <T> void invokeInitMethods(Class<T> type, T t) throws IllegalAccessException, InvocationTargetException {
        Set<Method> methods = ReflectionUtils.getAllMethods(type);
        for (Method method : methods) {
            if (method.isAnnotationPresent(PostConstruct.class)) {
                method.setAccessible(true);
                method.invoke(t);
            }
        }
    }

    private <T> void configureFields(T t) throws Exception {
            for (ObjectConfigurator configurator : objectConfigurators) {
            configurator.configure(t);
        }
    }

    private <T> Class<T> resolveImpl(Class<T> type) {
        if (type.isInterface()) {
            Class<T> implClass = config.getImplClass(type);
            if (implClass == null) {
                Set<Class<? extends T>> classes = scanner.getSubTypesOf(type);
                if (classes.size() != 1) {
                    throw new IllegalStateException(type + "has 0 or more than one impl, please update your config");
                }
                implClass = (Class<T>) classes.iterator().next();
            }
            type = implClass;
        }
        return type;
    }


}
