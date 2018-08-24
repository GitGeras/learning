package com.db.heroes_game.characters;


public class Hobbit extends Character {

    public KickResult kick(Character c) {
        toCry();
        return KickResult.builder().build();
    }

    @Override
    protected void initState() {
        setHp(3);
        setPower(0);
    }

    private void toCry(){
        System.out.println("Cry");
    }
}
