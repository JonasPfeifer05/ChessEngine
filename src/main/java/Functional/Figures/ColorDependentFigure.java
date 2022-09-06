package Functional.Figures;

import util.Position;

import java.util.ArrayList;

/**
 * A Class for Figures whose MoveSet varies depending on the Color of the Figure.
 */
public abstract class ColorDependentFigure extends Figure {
    private final ArrayList<Position> moveSetsWhite;
    private final ArrayList<Position> moveSetsBlack;

    public ColorDependentFigure(boolean white, Position position, ArrayList<Position> moveSetsWhite, ArrayList<Position> moveSetsBlack) {
        super(white, position);
        this.moveSetsWhite = moveSetsWhite;
        this.moveSetsBlack = moveSetsBlack;
    }

    /**
     * @return MoveSet depending on the Color of the Figure
     */
    @Override
    public ArrayList<Position> getMoveSet() {
        return this.isWhite() ? moveSetsWhite : moveSetsBlack;
    }
}
