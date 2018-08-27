package com.db.my_spring.configurator;

public interface ProxyConfigurator {
    <T> T wrapWithProxy(Class<T> type, T t) throws Exception;
}
