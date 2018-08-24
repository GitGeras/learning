package com.db.heroes_game.characters;

public class Sword implements Weapon {
    @Override
    public KickResult kick(Character c1, Character c2) {
        int damage = RandomFactory.getInstance().getNumberBetween(0, c1.getPower());
        c2.decreaseHp(damage);
        return KickResult.builder().hpDamage(damage).build();
    }
}
