package com.db.heroes_game.characters;

public interface RandomKick {

    int getRandom();

    default void kick(Character c) {
        int random = getRandom();
        c.setHp(c.getHp() - random);
    }
}
