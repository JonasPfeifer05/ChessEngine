package functional;

import functional.figure.figures.King;
import util.Player;
import util.Position;
import util.exceptions.InvalidMoveException;

import java.util.ArrayList;

/**
 * Created: 10.09.2022
 *
 * @author Jonas Pfeifer (jonas)
 */

public class Game {
	private int currentPlayerIndex = 0;
	public final Engine engine;

	public Game(int playerCount) {
		this.engine = new Engine(playerCount);
	}

	public Player getCurrentPlayer() {
		return Player.values()[currentPlayerIndex];
	}

	public boolean lookCheck(Player player) {
		return true;
	}

	private void nextPlayer() {
		currentPlayerIndex++;
		currentPlayerIndex %= 4;
	}

	public ArrayList<Position> getAllValidMoves(Position from) {
		if (engine.board.getFigure(from).getPlayer() != getCurrentPlayer()) return new ArrayList<>();

		ArrayList<Position> validMoves = engine.getAllValidMoves(from);

		ArrayList<Position> tempValid = new ArrayList<>();

		if (engine.board.getFigure(from) instanceof King) {
			if (lookCheck(engine.board.getPlayer(from))) {
				ArrayList<Position> invalidMoves = getInvalidKingMoves(from);
				for (int i = 0; i < validMoves.size(); i++) {
					Position abs = Position.add(validMoves.get(i), from);
					for (int j = 0; j < invalidMoves.size(); j++) {
						if (!abs.equals(invalidMoves.get(j))) {
							tempValid.add(validMoves.get(i));
						}
					}
				}
				return tempValid;
			}
		}

		return validMoves;
	}

	public ArrayList<Position> getInvalidKingMoves(Position from) {
		ArrayList<Position> invalid = new ArrayList<>();

		Player king = engine.board.getPlayer(from);

		ArrayList<Position> allFigures = engine.board.getAllPositions();
		ArrayList<Position> allAttacks = new ArrayList<>();

		for (Position figure : allFigures) {
			if (engine.board.getFigure(figure).getPlayer() == king) continue;
			allAttacks.addAll(engine.getAllValidAttacksAbsKing(figure));
		}

		ArrayList<Position> kingMoves = engine.getAllValidMoves(from);
		for (int i = 0; i < kingMoves.size(); i++) {
			kingMoves.set(i, Position.add(kingMoves.get(i), from));
		}

		for (Position kingMove : kingMoves) {
			for (Position Attack : allAttacks) {
				if (kingMove.equals(Attack)) {
					invalid.add(kingMove);
					break;
				}
			}
		}

		return invalid;
	}

	public void move(Position from, Position to) throws InvalidMoveException {
		if (engine.board.getFigure(from).getPlayer() != getCurrentPlayer()) throw new InvalidMoveException("Invalid Player selected");

		engine.board.onRoundStart();
		engine.move(from ,to);
		engine.board.onRoundEnd();
		nextPlayer();
	}
}
