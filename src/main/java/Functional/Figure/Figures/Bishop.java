package Functional.Figure.Figures;

import Functional.Figure.ColorIndependentFigure;
import util.Position;

import java.util.ArrayList;

public class Bishop extends ColorIndependentFigure {
	public static final ArrayList<Position> moveSets = new ArrayList<>();

	static {
		moveSets.add(new Position(1, 1));
		moveSets.add(new Position(-1, -1));
		moveSets.add(new Position(-1, 1));
		moveSets.add(new Position(1, -1));
	}

	public Bishop(boolean white) {
		super(white, moveSets, 7);
	}
}
