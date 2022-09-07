package Functional.Figure.Figures;

import Functional.Figure.ColorDependentFigure;
import util.Position;

import java.util.ArrayList;

public class Pawn extends ColorDependentFigure {
    public static final ArrayList<Position> moveSetsWhite = new ArrayList<>();
    public static final ArrayList<Position> moveSetsBlack = new ArrayList<>();

    static {
        moveSetsWhite.add(new Position(0, 1));
        moveSetsBlack.add(new Position(0, -1));
    }

    public Pawn(boolean white) {
        super(white, moveSetsWhite, moveSetsBlack, 1);
    }
}
