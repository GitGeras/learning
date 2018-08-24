package com.db.heroes_game.characters;

public class Elf extends Character {

    public KickResult kick(Character c) {
        KickResult.KickResultBuilder resultBuilder = KickResult.builder();
        int enemyPower = c.getPower();
        if (enemyPower < this.getPower()){
            resultBuilder.hpDamage(c.getHp());
            c.setHp(0);
        }
        else{
            c.decreasePower(1);
            resultBuilder.powerDamage(1);
        }
        return resultBuilder.build();
    }

    @Override
    protected void initState() {
        setHp(10);
        setPower(10);
    }
}
