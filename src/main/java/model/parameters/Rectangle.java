package model.parameters;

import javax.xml.bind.annotation.XmlType;

/**
 * class describing rectangle
 */
@XmlType(propOrder = {"x","y","width","height"})
public class Rectangle {
    private Integer x;
    private Integer y;
    private Integer width;
    private Integer height;

    public Rectangle(){}

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

    public void setVector(Vector vector){
        x=vector.getX();
        y=vector.getY();
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public void setHeight(Integer height) {
        this.height = height;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rectangle rectangle = (Rectangle) o;

        if (x != null ? !x.equals(rectangle.x) : rectangle.x != null) return false;
        if (y != null ? !y.equals(rectangle.y) : rectangle.y != null) return false;
        if (width != null ? !width.equals(rectangle.width) : rectangle.width != null) return false;
        return height != null ? height.equals(rectangle.height) : rectangle.height == null;
    }

    @Override
    public int hashCode() {
        int result = x != null ? x.hashCode() : 0;
        result = 31 * result + (y != null ? y.hashCode() : 0);
        result = 31 * result + (width != null ? width.hashCode() : 0);
        result = 31 * result + (height != null ? height.hashCode() : 0);
        return result;
    }
}
