package com.db.java8;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum Seniority {
    JUNIOR(0,1000),
    MIDDLE(1001,3000),
    SENIOR(3001, Integer.MAX_VALUE);

    private final int min;
    private final int max;

    public static Seniority findBySalary(int salary) {
        return Arrays.stream(values())
                .filter(seniority -> seniority.min <= salary && salary <= seniority.max)
                .findAny().get();
    }
}
