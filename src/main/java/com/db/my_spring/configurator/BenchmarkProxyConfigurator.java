package com.db.my_spring.configurator;

import com.db.my_spring.annotation.Benchmark;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class BenchmarkProxyConfigurator implements ProxyConfigurator {
    @Override
    @SuppressWarnings("unchecked")
    public <T> T configure(Class<T> type, T t) throws Exception {
        if (type.isAnnotationPresent(Benchmark.class)) {
            return (T) Proxy.newProxyInstance(type.getClassLoader(), type.getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    Object retVal = wrapWithBenchmark(method, args, t);
                    return retVal;
                }
            });
        }

        Method[] declaredMethods = t.getClass().getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            if (declaredMethod.isAnnotationPresent(Benchmark.class)) {
                return (T) Proxy.newProxyInstance(type.getClassLoader(), type.getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object retVal;
                        if (method.getName().equals(declaredMethod.getName()) &&
                                Arrays.equals(method.getParameterTypes(), declaredMethod.getParameterTypes())) {
                            retVal = wrapWithBenchmark(method, args, t);
                        } else {
                            retVal = method.invoke(t, args);
                        }
                        return retVal;
                    }
                });
            }
        }
        return t;
    }

    private <T> Object wrapWithBenchmark(Method method, Object[] args, T t) throws IllegalAccessException, InvocationTargetException {
        System.out.println("********** benchmark for method " + method.getName() + " was started ***********");
        long start = System.nanoTime();
        Object retVal = method.invoke(t, args);
        long end = System.nanoTime();
        System.out.println(end - start);
        System.out.println("********** benchmark for method " + method.getName() + " was ended ***********");
        return retVal;
    }
}
