package functional.figure.figures;

import functional.Board;
import functional.figure.PlayerIndependentFigure;
import util.Player;
import util.Position;

import java.util.ArrayList;

public class King extends PlayerIndependentFigure {
    public static final ArrayList<Position> moveDirections = new ArrayList<>();

    static {
        moveDirections.add(new Position(1, 1));
        moveDirections.add(new Position(-1, -1));
        moveDirections.add(new Position(-1, 1));
        moveDirections.add(new Position(1, -1));
    }

	public King(Player player) {
		super(player, moveDirections, moveDirections, 1, 1);
	}

	@Override
	public ArrayList<Position> getConditionalMoves(Board board, Position from) {
		ArrayList<Position> ret = new ArrayList<>();

		Position look1 = new Position(3,0);
		Position look2 = new Position(4,0);

		if (board.getFigure(Position.add(from, look1)) instanceof Knight) {
			if (board.checkClearance(true, from, 2, true)) {
				ret.add(new Position(2,0));
			}
		}
		if (board.getFigure(Position.add(from, look2)) instanceof Knight) {
			if (board.checkClearance(true, from, 3, true)) {
				ret.add(new Position(2,0));
			}
		}
		if (board.getFigure(Position.add(from, Position.mul(look1, -1))) instanceof Knight) {
			if (board.checkClearance(true, from, -2, true)) {
				ret.add(new Position(-2,0));
			}
		}
		if (board.getFigure(Position.add(from, Position.mul(look2, -1))) instanceof Knight) {
			if (board.checkClearance(true, from, -3, true)) {
				ret.add(new Position(-2,0));
			}
		}

		return ret;
	}

	@Override
	public void actionOnConditionalMove(Board board, Position from, Position move) {
		if (move.x == 2) {
			if (board.getFigure(Position.add(from, 3, 0)) instanceof Knight) {
				board.move(Position.add(from, 3, 0), Position.add(from, 1, 0));
			} else {
				board.move(Position.add(from, 4, 0), Position.add(from, 1, 0));
			}
		} else {
			if (board.getFigure(Position.add(from, -3, 0)) instanceof Knight) {
				board.move(Position.add(from, -3, 0), Position.add(from, -1, 0));
			} else {
				board.move(Position.add(from, -4, 0), Position.add(from, -1, 0));
			}
		}
	}
}
