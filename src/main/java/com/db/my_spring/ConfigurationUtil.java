package com.db.my_spring;

public interface ConfigurationUtil {
    <T> Class<? extends T> getImplClass(Class<T> name);
}
