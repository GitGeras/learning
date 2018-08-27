package com.db.my_spring.configurator;

import com.db.my_spring.BenchmarkToggle;
import com.db.my_spring.annotation.Benchmark;
import org.reflections.ReflectionUtils;
import org.springframework.cglib.proxy.Enhancer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class BenchmarkProxyConfigurator implements ProxyConfigurator {
    private BenchmarkToggle benchmarkToggle = new BenchmarkToggle();

    @Override
    public <T> T wrapWithProxy(Class<T> type, T t) {
        boolean methodNeedsBenchmark = ReflectionUtils.getAllMethods(type).stream().anyMatch(method -> method.isAnnotationPresent(Benchmark.class));
        if (type.isAnnotationPresent(Benchmark.class)||methodNeedsBenchmark) {
            if (type.getInterfaces().length == 0) {
                return (T) Enhancer.create(type, (org.springframework.cglib.proxy.InvocationHandler) (o, method, args) -> invoke(t, type, method, args));
            }
            else {
                return (T) Proxy.newProxyInstance(type.getClassLoader(), type.getInterfaces(), (proxy, method, args) -> invoke(t, type, method, args));
            }
        }
        return t;
    }

    private Object invoke(Object t, Class type, Method method, Object[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method classMethod = type.getMethod(method.getName(), method.getParameterTypes());
        if (benchmarkToggle.isEnabled()
                && (classMethod.isAnnotationPresent(Benchmark.class)
                || type.isAnnotationPresent(Benchmark.class))) {
            System.out.println("********** benchmark for method " + method.getName() + " was started ***********");
            long start = System.nanoTime();
            Object retVal = method.invoke(t, args);
            long end = System.nanoTime();
            System.out.println(end - start);
            System.out.println("********** benchmark for method " + method.getName() + " was ended ***********");
            return retVal;
        } else {
            return method.invoke(t, args);
        }
    }
}
