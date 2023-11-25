package entity.system;

import entity.unit.Unit;

public class Player {
    private String name;
    private Unit[] units;

    public Player(String name) {
        this.setName(name);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Unit[] getUnits() {
        return this.units;
    }

    public void setUnits(Unit[] units) throws Exception {
        if (units.length != 3) {
            throw new Exception();
        }

        this.units = units;
    }
}
