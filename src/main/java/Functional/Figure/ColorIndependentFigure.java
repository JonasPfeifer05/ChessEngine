package Functional.Figure;

import util.Position;

import java.util.ArrayList;

/**
 * A Class for Figures whose MoveSet is independent of the Color of the Figure.
 */
public abstract class ColorIndependentFigure extends Figure {
    private final ArrayList<Position> moveSets;

    public ColorIndependentFigure(boolean white, ArrayList<Position> moveSets) {
        super(white);
        this.moveSets = moveSets;
    }

    @Override
    public ArrayList<Position> getMoveSet() {
        return moveSets;
    }
}