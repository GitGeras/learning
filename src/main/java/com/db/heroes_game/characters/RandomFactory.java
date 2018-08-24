package com.db.heroes_game.characters;

public class RandomFactory {

    private static RandomFactory ourInstance = new RandomFactory();

    public static RandomFactory getInstance() {
        return ourInstance;
    }

    private RandomFactory() {
    }

    public int getNumberBetween(int min, int max) {
        return (int) (Math.random() * (max - min)) + min;
    }
}
