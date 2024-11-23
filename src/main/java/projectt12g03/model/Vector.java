package projectt12g03.model;

public class Vector {
    private double x;
    private double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof Vector)){return false;}
        return ((Vector) o).getX() == this.getX() && ((Vector) o).getY() == this.getY();
    }
}
