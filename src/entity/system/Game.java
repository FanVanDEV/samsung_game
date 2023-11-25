package entity.system;

import entity.system.type.PlayType;
import entity.system.type.State;
import entity.unit.Knight;
import entity.unit.Terminator;
import entity.unit.Unit;
import entity.unit.Wizard;

public class Game {
    private String name;
    private final Player[] players = new Player[2];
    private PlayType playType;
    private final Scenary scenary = new Scenary();

    public Game(String name) {
        this.setName(name);
        this.scenary.setState(State.ONBOARDING);
    }

    public void play() {
        this.scenary.setState(State.INGAME);
        this.scenary.nextRound();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Player[] getPlayers() {
        return this.players;
    }

    public void setPlayType(String play) throws Exception {
        switch (play) {
            case "A" -> this.playType = PlayType.AUTO;
            case "HA" -> this.playType = PlayType.HALF_AUTO;
            case "M" -> this.playType = PlayType.MANUAL;
            default -> throw new Exception();
        }
    }

    public void addPlayer(int id, String name) throws Exception {
        Player player = new Player(name);

        if (players[id] != null) {
            throw new Exception();
        }

        players[id] = player;
    }

    public void setHeroesToPlayer(Player player, String[] rawHeroes) throws Exception {
        if (rawHeroes.length != 3) {
            throw new Exception();
        }

        Unit[] heroes = new Unit[3];
        for (int i = 0; i < rawHeroes.length; i++) {
            String hero = rawHeroes[i];

            if (hero.length() != 1) {
                throw new Exception();
            }
            if (!hero.equals("K") && !hero.equals("W") && !hero.equals("T")) {
                throw new Exception();
            }

            switch (hero) {
                case "K" -> heroes[i] = new Knight();
                case "W" -> heroes[i] = new Wizard();
                case "T" -> heroes[i] = new Terminator();
            }
        }

        player.setUnits(heroes);
    }

    public int getRound() {
        return this.scenary.getRound();
    }

    public boolean isAllPlayersAlive() {
        Player[] players = this.getPlayers();
        for (Player player : players) {
            Unit[] heroes = player.getUnits();

            for (Unit hero : heroes) {
                if (hero.getHealth() != 0) {
                    return true;
                }
            }
        }

        return false;
    }
}
