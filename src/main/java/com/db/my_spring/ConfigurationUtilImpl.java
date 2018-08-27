package com.db.my_spring;

import java.util.HashMap;

public class ConfigurationUtilImpl implements ConfigurationUtil {

    private HashMap<Class, Class> config = new HashMap<>();

    {
        config.put(Cleaner.class, CleanerImpl.class);
//        config.put(Speaker.class, ConsoleSpeaker.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Class<T> getImplClass(Class<T> name) {
        return config.get(name);
    }
}
