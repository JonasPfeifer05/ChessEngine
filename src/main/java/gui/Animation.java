package gui;

import functional.figure.Figure;
import util.Position;

/**
 * Created: 09.09.2022
 *
 * @author Tobias Frischmann
 */
public class Animation {

    private final Figure figure;

    private final Position startPos;
    private final Position endPos;
    private final Position currentPos;

    public Animation(Figure figure, Position startPos, Position endPos) {
        this.figure = figure;
        this.startPos = startPos;
        this.endPos = endPos;

        currentPos = startPos.copy();
    }
}
