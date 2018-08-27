package com.db.my_spring.configurator;

public interface ProxyConfigurator {
    <T> T configure(Class<T> type, T t) throws Exception;
}
