package Functional.Figure.Figures;

import Functional.Figure.ColorIndependentFigure;
import util.Position;

import java.util.ArrayList;

public class Bishop extends ColorIndependentFigure {
    public static final ArrayList<Position> moveSets = new ArrayList<>();

    static {
        for (int i = 1; i < 8; i++) {
            moveSets.add(new Position(i,i));
            moveSets.add(new Position(-i,-i));
            moveSets.add(new Position(-i,i));
            moveSets.add(new Position(i,-i));
        }
    }

    public Bishop(boolean white) {
        super(white, moveSets);
    }
}
