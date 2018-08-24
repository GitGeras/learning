package com.db.my_spring.configurator;

import com.db.my_spring.annotation.InjectRandomInt;
import org.fluttercode.datafactory.impl.DataFactory;

import java.lang.reflect.Field;

public class InjectRandomIntConfigurator implements ObjectConfigurator {
    @Override
    public void configure(Object obj) throws IllegalAccessException {
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (declaredField.isAnnotationPresent(InjectRandomInt.class)) {
                InjectRandomInt annotation = declaredField.getAnnotation(InjectRandomInt.class);
                DataFactory dataFactory = new DataFactory();
                int numberBetween = dataFactory.getNumberBetween(annotation.min(), annotation.max() + 1);
                declaredField.setAccessible(true);
                declaredField.setInt(obj, numberBetween);
            }
        }
    }
}
