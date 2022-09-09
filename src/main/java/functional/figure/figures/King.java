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
		moveDirections.add(new Position(1, 0));
		moveDirections.add(new Position(-1, 0));
		moveDirections.add(new Position(0, -1));
		moveDirections.add(new Position(0, 1));
	}

	public King(Player player) {
		super(player, moveDirections, moveDirections, 1, 1);
	}

	@Override
	public ArrayList<Position> getConditionalMoves(Board board, Position from) {
		ArrayList<Position> ret = new ArrayList<>();

		int s = 3;
		int l = 4;

		for (int i = -1; i <= 1; i+=2) {
			if (board.getPlayer(from) == Player.PLAYER1 || board.getPlayer(from) == Player.PLAYER2) {
				try {
					if (board.getFigure(Position.add(from, new Position(i*s,0))) instanceof Rook) {
						if (board.checkClearance(true, from, i*s, true)) {
							ret.add(new Position(i*2, 0));
						}
					}
				} catch (Exception e) {
				}

				try {
					if (board.getFigure(Position.add(from, new Position(i*l,0))) instanceof Rook) {
						if (board.checkClearance(true, from, i*l, true)) {
							ret.add(new Position(i*2, 0));
						}
					}
				} catch (Exception e) {
				}
			} else {
				try {
					if (board.getFigure(Position.add(from, new Position(0,i*s))) instanceof Rook) {
						if (board.checkClearance(false, from, i*s, true)) {
							ret.add(new Position(0,i*2));
						}
					}
				} catch (Exception e) {
				}

				try {
					if (board.getFigure(Position.add(from, new Position(0,i*l))) instanceof Rook) {
						if (board.checkClearance(false, from, i*l, true)) {
							ret.add(new Position(0,i*2));
						}
					}
				} catch (Exception e) {
				}
			}
		}

		return ret;
	}

	@Override
	public void actionOnConditionalMove(Board board, Position from, Position move) {
		if (move.x == 2) {
			if (board.getFigure(Position.add(from, 3, 0)) instanceof Rook) {
				board.move(Position.add(from, 3, 0), Position.add(from, 1, 0));
			} else {
				board.move(Position.add(from, 4, 0), Position.add(from, 1, 0));
			}
		} else if (move.x == -2){
			if (board.getFigure(Position.add(from, -3, 0)) instanceof Rook) {
				board.move(Position.add(from, -3, 0), Position.add(from, -1, 0));
			} else {
				board.move(Position.add(from, -4, 0), Position.add(from, -1, 0));
			}
		} else if (move.y == 2) {
			if (board.getFigure(Position.add(from, 0, 3)) instanceof Rook) {
				board.move(Position.add(from, 0, 3), Position.add(from, 0, 1));
			} else {
				board.move(Position.add(from, 0, 4), Position.add(from, 0, 1));
			}
		} else {
			if (board.getFigure(Position.add(from, 0, -3)) instanceof Rook) {
				board.move(Position.add(from, 0, -3), Position.add(from, 0, -1));
			} else {
				board.move(Position.add(from, 0, -4), Position.add(from, 0, -1));
			}
		}
	}
}
