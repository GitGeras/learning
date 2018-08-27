package com.db.my_spring;

import lombok.SneakyThrows;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {
        ObjectFactory objectFactory = ObjectFactory.getInstance();
        IRobot iRobot = objectFactory.createObject(IRobot.class);
        while (true) {
            iRobot.cleanRoom();
            Thread.sleep(1000);
        }

        /*BeerService beerService = ObjectFactory.getInstance().createObject(BeerService.class);
        beerService.drinkBeer();
        beerService.goToToilet();*/
    }
}
