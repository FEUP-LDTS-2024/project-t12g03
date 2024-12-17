package projectt12g03.model;

public class Vector {
    private int x;
    private int y;

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof Vector)){return false;}
        return ((Vector) o).getX() == this.getX() && ((Vector) o).getY() == this.getY();
    }

    public void add(Vector v){
        x += v.getX();
        y += v.getY();
    }
}
