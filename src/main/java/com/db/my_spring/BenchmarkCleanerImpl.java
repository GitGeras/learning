package com.db.my_spring;

import com.db.my_spring.annotation.InjectByType;

public class BenchmarkCleanerImpl implements Cleaner {

    @InjectByType
    private CleanerImpl cleaner;

    @Override
    public void clean() {
        long start = System.nanoTime();
        cleaner.clean();
        long finish = System.nanoTime();
        System.out.println(finish-start);
    }
}
