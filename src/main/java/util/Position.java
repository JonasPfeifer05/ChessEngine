package util;

public class Position {
    public int x;
    public int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Position add(Position position1, Position position2) {
        return new Position(position1.x + position2.x, position1.y + position2.y);
    }

    public static Position add(Position position, int x, int y) {
        return new Position(position.x+x, position.y+y);
    }

    public static Position mul(Position position1, int x) {
        return new Position(position1.x * x, position1.y * x);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public String toString() {
        return String.format("Position[x=%d, y=%d]", x, y);
    }
}
