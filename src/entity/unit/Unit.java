package entity.unit;


import util.Luck;

public abstract class Unit {
    protected String name;
    protected int health;
    protected int damage;
    protected int criticalChance;
    protected int pairChance;
    public int getHealth() {
        return this.health;
    }

    public String getName() {
        return this.name;
    }
    public int takeDamage(int damage) {
        if (Luck.chance(this.pairChance))
            return -1;

        this.health = damage >= this.health ? 0 : this.health - damage;

        return this.health;
    }

    public int attack() {
        return Luck.chance(this.criticalChance) ? this.damage : this.damage * 2;
    }

    public int getDamage() {
        return this.damage;
    }
}
