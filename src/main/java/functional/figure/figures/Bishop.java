package functional.figure.figures;

import functional.figure.PlayerIndependentFigure;
import util.Asset;
import util.Player;
import util.Position;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Bishop extends PlayerIndependentFigure {
	public static final ArrayList<Position> moveDirections = new ArrayList<>();

	static {
		moveDirections.add(new Position(1, 1));
		moveDirections.add(new Position(-1, -1));
		moveDirections.add(new Position(-1, 1));
		moveDirections.add(new Position(1, -1));
	}

	public Bishop(Player player) {
		super(player, moveDirections, moveDirections, 13, 13);
	}
}
