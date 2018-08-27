package com.db.my_spring;

import com.db.my_spring.annotation.Benchmark;

import javax.annotation.PostConstruct;

@Benchmark
public class BeerServiceImpl implements BeerService {
    @Override
    public void drinkBeer() {
        System.out.println("Blanche is the best");
    }

    @Override
//    @Benchmark
    public void goToToilet() {
        System.out.println("pssssssssss");
    }

    @PostConstruct
    private void init(){
        System.out.println("init");
    }
}
