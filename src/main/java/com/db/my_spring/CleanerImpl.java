package com.db.my_spring;

import com.db.my_spring.annotation.Benchmark;
import com.db.my_spring.annotation.InjectRandomInt;

import javax.annotation.PostConstruct;

@Benchmark
public class CleanerImpl implements Cleaner {

    @InjectRandomInt(min = 3, max = 6)
    private int repeat;

    @PostConstruct
    public void init() {
        System.out.println("Init");
    }

    @Override
    public void clean() {
        for (int i = 0; i < repeat; i++) {
            System.out.println("vvvVVVVvvv");
        }
    }
}
