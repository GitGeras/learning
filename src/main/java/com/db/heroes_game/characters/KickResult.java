package com.db.heroes_game.characters;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KickResult {
    private int hpDamage;
    private int powerDamage;
}
