package Functional.Figure.Figures;

import Functional.Figure.ColorIndependentFigure;
import util.Position;

import java.util.ArrayList;

public class King extends ColorIndependentFigure {
    public static final ArrayList<Position> moveSets = new ArrayList<>();

    static {
        int i = 1;
        moveSets.add(new Position(i, i));
        moveSets.add(new Position(-i, -i));
        moveSets.add(new Position(-i, i));
        moveSets.add(new Position(i, -i));
    }

    public King(boolean white) {
        super(white, moveSets);
    }
}
