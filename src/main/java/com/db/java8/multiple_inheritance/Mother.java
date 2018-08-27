package com.db.java8.multiple_inheritance;

public interface Mother {
    default void speak() {
        System.out.println("Mother");
    }
}
