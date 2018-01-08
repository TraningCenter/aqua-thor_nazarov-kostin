package model.parameters;

public class Flow {

    private Vector direction;
    private Integer strength;
    private Rectangle rectangle;

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

    public Flow(Vector direction, Integer strength, Rectangle rectangle) {
        this.direction = direction;
        this.strength = strength;
        this.rectangle = rectangle;
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

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    @Override
    public String toString() {
        return "Flow{" +
                "direction=" + direction +
                ", strength=" + strength +
                ", rectangle=" + rectangle +
                '}';
    }
}
