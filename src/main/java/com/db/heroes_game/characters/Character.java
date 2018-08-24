package com.db.heroes_game.characters;

import lombok.Data;

@Data
public abstract class Character {
    private int power;
    private int hp;

    public Character() {
        initState();
    }

    public abstract KickResult kick(Character c);

    protected abstract void initState();

    public boolean isAlive(){
        return hp > 0;
    }

    public void decreaseHp(int delta) {
        hp -= delta;
    }
    public void decreasePower(int delta) {
        power -= delta;
    }
}
