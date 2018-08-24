package com.db.my_spring;

import com.db.my_spring.annotation.Benchmark;
import com.db.my_spring.configurator.ObjectConfigurator;
import lombok.SneakyThrows;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;

import javax.annotation.PostConstruct;
import java.lang.reflect.*;
import java.util.HashSet;
import java.util.Set;

public class ObjectFactory {
    private static ObjectFactory ourInstance = new ObjectFactory();
    private ConfigurationUtil configUtil;
    private Reflections scanner = new Reflections("com.db.my_spring");
    private Set<ObjectConfigurator> configurators = new HashSet<>();

    @SneakyThrows
    private ObjectFactory() {
        Set<Class<? extends ObjectConfigurator>> objectConfiguratorClasses = scanner.getSubTypesOf(ObjectConfigurator.class);
        for (Class<? extends ObjectConfigurator> objectConfiguratorClass : objectConfiguratorClasses) {
            if (!Modifier.isAbstract(objectConfiguratorClass.getModifiers())) {
                configurators.add(objectConfiguratorClass.newInstance());
            }
        }
    }

    public static ObjectFactory getInstance() {
        return ourInstance;
    }

    @SneakyThrows
    @SuppressWarnings("unchecked")
    public <T>  T createObjectWithoutAnnotation(Class<T> type) {
        T newInstance = createImpl(type);

        return newInstance;
    }

    @SneakyThrows
    @SuppressWarnings("unchecked")
    public <T>  T createObject(Class<T> type) {
        T t = createImpl(type);
        configure(t);

        invokeInitMethod(type, t);

        if (type.isAnnotationPresent(Benchmark.class)) {
            return (T) Proxy.newProxyInstance(type.getClassLoader(), type.getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("*** benchmarke for method " + method.getName() + " was started");
                    long start = System.nanoTime();
                    Object retVal = method.invoke(t, args);
                    long end = System.nanoTime();
                    System.out.println("*** benchmarke for method " + method.getName() + " was ended");
                    return retVal;
                }
            });
        }

        return t;
    }

    private <T> void invokeInitMethod(Class<T> type, T newInstance) throws IllegalAccessException, InvocationTargetException {
        Set<Method> methods = ReflectionUtils.getAllMethods(type);
        for (Method method : methods) {
            if (method.isAnnotationPresent(PostConstruct.class)) {
                method.setAccessible(true);
                method.invoke(newInstance);
            }
        }
    }

    private <T> T createImpl(Class<T> type) throws InstantiationException, IllegalAccessException {
        T newInstance;
        Class<? extends T> implClass;
        if (type.isInterface()) {
            configUtil = new ConfigurationUtilImpl();
            implClass = configUtil.getImplClass(type);
            if (implClass == null) {
                Set<Class<? extends T>> classes = scanner.getSubTypesOf(type);
                if (classes.size() != 1) {
                    throw new IllegalStateException(type + "has 0 or more than 1 impl, please update your config");
                }
                implClass = classes.iterator().next();
            }
            newInstance = implClass.newInstance();
        } else {
            newInstance = type.newInstance();
        }
        return newInstance;
    }

    public <T> void configure(T obj) throws IllegalAccessException, InvocationTargetException {
        for (ObjectConfigurator configurator : configurators) {
            configurator.configure(obj);
        }
    }
}
