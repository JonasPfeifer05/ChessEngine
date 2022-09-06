package Functional.Figure.Figures;

import Functional.Figure.ColorIndependentFigure;
import util.Position;

import java.util.ArrayList;

public class Knight extends ColorIndependentFigure {
    public static final ArrayList<Position> moveSets = new ArrayList<>();

    static {
        int s = 1;
        int l = 2;
        moveSets.add(new Position(s, l));
        moveSets.add(new Position(l, s));

        moveSets.add(new Position(-s, -l));
        moveSets.add(new Position(-l, -s));

        moveSets.add(new Position(-s, l));
        moveSets.add(new Position(-l, s));

        moveSets.add(new Position(s, -l));
        moveSets.add(new Position(l, -s));

    }

    public Knight(boolean white) {
        super(white, moveSets);
    }
}
