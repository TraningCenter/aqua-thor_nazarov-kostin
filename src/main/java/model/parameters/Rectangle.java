package model.parameters;

public class Rectangle {
    private Integer x;
    private Integer y;
    private Integer width;
    private Integer height;

    public Rectangle(Integer x, Integer y, Integer width, Integer height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    public boolean isInside(Vector position){

        return position.getX()>=x && position.getX()<x+width && position.getY()>=y && position.getY()<y+height;

    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
