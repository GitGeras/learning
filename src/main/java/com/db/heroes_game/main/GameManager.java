package com.db.heroes_game.main;

import com.db.heroes_game.characters.Character;

public class GameManager {

    public void fight(Character c1, Character c2){
        boolean first = true;

        while (c1.isAlive() && c2.isAlive()){
            System.out.println(c1.getHp());
            System.out.println(c2.getHp());

            if (first) {
                c1.kick(c2);
                first = false;
            } else{
                c2.kick(c1);
                first = true;
            }
        }

        if (c1.isAlive()){
            System.out.println(c1.getClass() + " won");
        }
        else
        {
            System.out.println(c2.getClass() + " won");
        }
    }
}
