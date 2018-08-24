package com.db.heroes_game.main;

import com.db.heroes_game.characters.Character;

public class Main {
    public static void main(String[] args) {
        CharacterFactory cf = new CharacterFactory();

        Character c1 = cf.createCharacter();
        System.out.println("Create " + c1.getClass());
        Character c2 = cf.createCharacter();
        System.out.println("Create " + c2.getClass());

        GameManager gm = new GameManager();
        gm.fight(c1, c2);
    }
}
