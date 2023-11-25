package entity.unit;

import util.Luck;

public class Knight extends Unit {
    public Knight() {
        this.name = "Knight";

        this.health = 15;

        this.criticalChance = 30;
        this.pairChance = 30;
        this.damage = 30;
    }
}
