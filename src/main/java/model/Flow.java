package model;

public class Flow {
    private Vector direction;
    private Integer strength;

    public Flow(Vector direction, Integer strength) {
        this.direction = direction;
        this.strength = strength;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Vector getDirection() {
        return direction;
    }

    public void setDirection(Vector direction) {
        this.direction = direction;
    }
}
