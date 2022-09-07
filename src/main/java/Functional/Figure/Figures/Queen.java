package Functional.Figure.Figures;

import Functional.Figure.ColorIndependentFigure;
import util.Position;

import java.util.ArrayList;

public class Queen extends ColorIndependentFigure {
    public static final ArrayList<Position> moveSets = new ArrayList<>();

    static {
        for (int i = 1; i < 8; i++) {
            moveSets.add(new Position(i,i));
            moveSets.add(new Position(-i,-i));
            moveSets.add(new Position(-i,i));
            moveSets.add(new Position(i,-i));

            moveSets.add(new Position(i,0));
            moveSets.add(new Position(-i,0));
            moveSets.add(new Position(0,-i));
            moveSets.add(new Position(0,-i));
        }
    }

    public Queen(boolean white) {
        super(white, moveSets);
    }
}
