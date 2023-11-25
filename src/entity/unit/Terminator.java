package entity.unit;

public class Terminator extends Unit {
    public Terminator() {
        this.name = "Terminator";

        this.health = 500;

        this.criticalChance = 70;
        this.pairChance = 30;
        this.damage = 20;
    }
}
