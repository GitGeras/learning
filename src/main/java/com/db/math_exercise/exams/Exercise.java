package com.db.math_exercise.exams;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Exercise {
    private int a;
    private int b;
    private Operation operation;
    private int answer;

    @Override
    public String toString() {
        return a + " " + operation + " " + b + " = " + answer;
    }
}
