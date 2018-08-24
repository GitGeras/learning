package com.db.my_spring.refactor;

/**
 * @author Evgeny Borisov
 */
public interface ProxyConfigurator {
    <T> T configure(Class<T> type, T t) throws Exception;
}
