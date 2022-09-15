package functional.figure.special;

import functional.Board;
import functional.figure.Figure;
import util.Player;
import util.Position;

import java.util.ArrayList;

/**
 * Created: 08.09.2022
 *
 * @author Jonas Pfeifer (jonas)
 */

public class KillLinked extends Figure {

	private Position pos;
	private Position pawn;

	private static int roundCountStart;

	private int roundCount = roundCountStart;

	public KillLinked(Player player, Position pos, Position pawn) {
		super(player, 0, 0);
		this.pos = pos;
		this.pawn = pawn;
	}

	public static void setRoundCount(int count) {
		roundCountStart = count;
	}

	@Override
	public ArrayList<Position> getMoveDirections() {
		return new ArrayList<>();
	}

	@Override
	public ArrayList<Position> getAttackDirections() {
		return new ArrayList<>();
	}

	@Override
	public void actionOnRoundStart(Board board, Position from) {
		roundCount--;
		if (roundCount <= 0) board.set(pos, null);
	}

	@Override
	public void kill(Board board) {
		board.set(pawn, null);
	}
}
