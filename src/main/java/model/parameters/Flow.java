package model.parameters;

public class Flow {

    private Rectangle rectangle;
    private Vector direction;
    private Integer strength;


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


    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }


    public Vector getDirection() {
        return direction;
    }

    public void setDirection(Vector direction) {
        this.direction = direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flow flow = (Flow) o;

        if (rectangle != null ? !rectangle.equals(flow.rectangle) : flow.rectangle != null) return false;
        if (direction != null ? !direction.equals(flow.direction) : flow.direction != null) return false;
        return strength != null ? strength.equals(flow.strength) : flow.strength == null;
    }

    @Override
    public int hashCode() {
        int result = rectangle != null ? rectangle.hashCode() : 0;
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        result = 31 * result + (strength != null ? strength.hashCode() : 0);
        return result;
    }
}
