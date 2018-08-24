package com.db.heroes_game.characters;

import lombok.AccessLevel;
import lombok.Setter;

public class King extends Character{

    @Setter(AccessLevel.PROTECTED)
    private Weapon weapon = new Sword();

    @Override
    public KickResult kick(Character c) {
        return weapon.kick(this, c);
    }

    @Override
    protected void initState() {
        setHp(RandomFactory.getInstance().getNumberBetween(5, 15));
        setPower(RandomFactory.getInstance().getNumberBetween(5, 15));
    }
}
