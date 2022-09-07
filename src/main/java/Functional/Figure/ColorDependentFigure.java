package Functional.Figure;

import util.Position;

import java.util.ArrayList;

/**
 * A Class for Figures whose MoveSet varies depending on the Color of the Figure.
 */
public abstract class ColorDependentFigure extends Figure {
    private final ArrayList<Position> moveDirectionsWhite;
    private final ArrayList<Position> moveDirectionBlack;

    public ColorDependentFigure(boolean white, ArrayList<Position> moveSetsWhite, ArrayList<Position> moveSetsBlack, int maxMoveDistance) {
        super(white, maxMoveDistance);
        this.moveDirectionsWhite = moveSetsWhite;
        this.moveDirectionBlack = moveSetsBlack;
    }

    /**
     * @return MoveSet depending on the Color of the Figure
     */
    @Override
    public ArrayList<Position> getMoveDirections() {
        return this.isWhite() ? moveDirectionsWhite : moveDirectionBlack;
    }
}
