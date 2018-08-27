package com.db.my_spring;

public interface ConfigurationUtil {
    <T> Class<T> getImplClass(Class<T> name);
}
