package no.org.World;

public class Position {

    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isIn(Position bottomLeft, Position topRight) {
        boolean withinTop = this.y <= topRight.getY();
        boolean withinBottom = this.y >= bottomLeft.getY();
        boolean withinLeft = this.x >= bottomLeft.getX();
        boolean withinRight = this.x <= topRight.getX();
        return withinTop && withinBottom && withinLeft && withinRight;
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}
