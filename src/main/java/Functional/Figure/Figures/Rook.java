package Functional.Figure.Figures;

import Functional.Figure.ColorIndependentFigure;
import util.Position;

import java.util.ArrayList;

public class Rook extends ColorIndependentFigure {
	public static final ArrayList<Position> moveSets = new ArrayList<>();

	static {
		moveSets.add(new Position(1, 0));
		moveSets.add(new Position(-1, 0));
		moveSets.add(new Position(0, -1));
		moveSets.add(new Position(0, -1));

	}

	public Rook(boolean white) {
		super(white, moveSets, 7);
	}
}
