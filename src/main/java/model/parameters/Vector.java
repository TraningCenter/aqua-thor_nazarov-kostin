package model.parameters;

public class Vector {
    private Integer x;
    private Integer y;

    public Vector(){}

    public Vector(Vector vector){
        this.x=vector.getX();
        this.y=vector.getY();
    }

    public Vector(Integer x, Integer y){
        this.x=x;
        this.y=y;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector vector = (Vector) o;

        if (!x.equals(vector.x)) return false;
        return y.equals(vector.y);
    }

    @Override
    public int hashCode() {
        int result = x.hashCode();
        result = 31 * result + y.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Vector{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public Vector minus(Vector other){
        return new Vector(this.getX() - other.getX(), this.getY() - other.getY());
    }

    public Vector plus(Vector other){
        return new Vector(this.getX() + other.getX(), this.getY() + other.getY());
    }

    public Double distanceTo(Vector other){
        return Math.sqrt(Math.pow(other.getX() - this.getX(),2)+Math.pow(other.getY() - this.getY(),2));
    }

    public static Vector Zero(){
        return new Vector(0,0);
    }

    public boolean equalsDimension(Vector other){
        return (this.getX()!=0&&other.getX()!=0) || (this.getY()!=0&&other.getY()!=0);
    }

    public boolean isSameDimensionDirection(Vector other){
        return (this.getX()*other.getX()>0)||(this.getY()*other.getY()>0);
    }
}
