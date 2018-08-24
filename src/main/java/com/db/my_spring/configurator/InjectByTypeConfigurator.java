package com.db.my_spring.configurator;

import com.db.my_spring.ObjectFactory;
import com.db.my_spring.annotation.InjectByType;
import com.db.my_spring.annotation.InjectRandomInt;
import org.fluttercode.datafactory.impl.DataFactory;

import java.lang.reflect.Field;

public class InjectByTypeConfigurator implements ObjectConfigurator {
    @Override
    public void configure(Object obj) throws IllegalAccessException {
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (declaredField.isAnnotationPresent(InjectByType.class)) {
                declaredField.setAccessible(true);
                Class<?> clazz = declaredField.getType();
                declaredField.set(obj, ObjectFactory.getInstance().createObject(clazz));
            }
        }
    }
}
