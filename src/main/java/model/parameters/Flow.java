package model.parameters;

public class Flow {

    private Vector direction;
    private Integer strength;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flow flow = (Flow) o;

        if (!direction.equals(flow.direction)) return false;
        return strength.equals(flow.strength);
    }

    @Override
    public int hashCode() {
        int result = direction.hashCode();
        result = 31 * result + strength.hashCode();
        return result;
    }


    public Flow(){}

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
