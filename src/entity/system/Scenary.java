package entity.system;

import entity.system.type.State;

public class Scenary {
    private int round = 0;
    private State state = State.ONBOARDING;

    public void nextRound() {
        this.updateRound();
    }

    public int getRound() {
        return round;
    }

    public State getState() {
        return state;
    }

    private void updateRound() {
        this.round++;
    }

    public void setState(State state) {
        this.state = state;
    }
}
