package com.db.java8.multiple_inheritance;

public class Daughter implements Father, Mother {
    @Override
    public void speak() {
        Mother.super.speak();
    }
}
