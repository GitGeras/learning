package com.db.java8;

@FunctionalInterface
public interface Equalator<T> {
    boolean equals(T t, T t1);
}
