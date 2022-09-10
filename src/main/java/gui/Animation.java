package gui;

import functional.figure.Figure;
import util.Position;

/**
 * Created: 09.09.2022
 *
 * @author Tobias Frischmann
 */
public class Animation {

    public final Figure figure;
    public final Position endPos;

    private float x;
    private float y;

    public Animation(Figure figure, Position startPos, Position endPos) {
        this.figure = figure;
        this.endPos = endPos;

        x = startPos.x;
        y = startPos.y;
    }

    public boolean update() {
        double dirX = endPos.x - x;
        double dirY = endPos.y - y;
        double length = Math.sqrt(Math.pow(dirX, 2) + Math.pow(dirY, 2));

        x += dirX / length * length / 10;
        y += dirY / length * length / 10;

        return length < 0.05;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
