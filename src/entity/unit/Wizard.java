package entity.unit;

public class Wizard extends Unit {
    public Wizard() {
        this.name = "Wizard";

        this.health = 200;

        this.criticalChance = 10;
        this.pairChance = 30;
        this.damage = 20;
    }
}
