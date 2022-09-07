package functional.figure.figures;

import functional.figure.PlayerIndependentFigure;
import util.Player;
import util.Position;

import java.util.ArrayList;

public class Rook extends PlayerIndependentFigure {
	public static final ArrayList<Position> moveDirections = new ArrayList<>();

	static {
		moveDirections.add(new Position(1, 0));
		moveDirections.add(new Position(-1, 0));
		moveDirections.add(new Position(0, -1));
		moveDirections.add(new Position(0, -1));

	}

	public Rook(Player player) {
		super(player, moveDirections, moveDirections, 7, 7);
	}
}
