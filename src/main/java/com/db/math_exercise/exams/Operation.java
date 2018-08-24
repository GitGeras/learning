package com.db.math_exercise.exams;

import lombok.RequiredArgsConstructor;

public enum Operation {
    PLUS("+"),
    MINUS("-"),
    MULTIPLY("*"),
    DIVIDE("-");

    private String sign;

    Operation(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return sign;
    }
}
