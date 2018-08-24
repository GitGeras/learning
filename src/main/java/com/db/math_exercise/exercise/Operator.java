package com.db.math_exercise.exercise;

public enum Operator {
    PLUS('+'),
    MINUS('-'),
    MULTIPLICATION('*'),
    DIVISION('/');

    char display;

    Operator(char ch){
        display = ch;
    }

    @Override
    public String toString() {
        return "" + display;
    }
}
