package model;

public class Flow {
    private Vector direction;
    private Vector strength;

    public Flow(Vector direction, Vector strength) {
        this.direction = direction;
        this.strength = strength;
    }

    public Vector getStrength() {
        return strength;
    }

    public void setStrength(Vector strength) {
        this.strength = strength;
    }

    public Vector getDirection() {
        return direction;
    }

    public void setDirection(Vector direction) {
        this.direction = direction;
    }
}
