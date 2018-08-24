package com.db.my_spring;

import com.db.my_spring.configurator.ObjectConfigurator;
import lombok.SneakyThrows;
import org.reflections.Reflections;

import java.lang.reflect.Modifier;
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
        T newInstance = createImpl(type);
        processAnnotation(newInstance);

        return newInstance;
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

    public <T> void processAnnotation(T obj) throws IllegalAccessException {
        for (ObjectConfigurator configurator : configurators) {
            configurator.configure(obj);
        }
    }
}
