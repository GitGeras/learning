package com.db.my_spring;

import lombok.SneakyThrows;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        ObjectFactory objectFactory = ObjectFactory.getInstance();
        IRobot iRobot = objectFactory.createObject(IRobot.class);
        objectFactory.processAnnotation(iRobot);
        iRobot.cleanRoom();
    }
}
