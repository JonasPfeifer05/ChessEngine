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

	public final boolean inOrder;

	public Game(int playerCount) {
		this(playerCount, true);
	}
	public Game(int playerCount, boolean inOrder) {
		this.engine = new Engine(playerCount);
		this.inOrder = inOrder;
	}

	public Player getCurrentPlayer() {
		return Player.values()[currentPlayerIndex];
	}

	public Position getKing(Player player) {
		for (Position pos : engine.board.getAllPositions()) {
			if (engine.board.getPlayer(pos) == player) {
				if (engine.board.getFigure(pos) instanceof King) return pos;
			}
		}

		return null;
	}

	public ArrayList<Position> getAllAttacksExceptFromPlayer(Player player) {
		ArrayList<Position> allAttacks = new ArrayList<>();

		for (Position figure : engine.board.getAllPositions()) {
			if (getKing(engine.board.getPlayer(figure)) == null) continue;
			if (engine.board.getFigure(figure).getPlayer() == player) continue;
			allAttacks.addAll(engine.getAllValidAttacksAbs(figure));
		}

		return allAttacks;
	}

	public boolean lookCheck(Player player) {
		Position king = getKing(player);
		if (king == null) return false;

		return getAllAttacksExceptFromPlayer(player).contains(king);
	}
	public boolean lookCheckMate(Player player) {
		if (!lookCheck(player)) return false;

		Position king = getKing(player);

		ArrayList<Position> allValidMoves = getAllValidMoves(king);
		allValidMoves.removeAll(getAllAttacksExceptFromPlayer(player));
		return allValidMoves.size() == 0;
	}

	public boolean lockMateIn(Player player) {
		return false;
	}

	private void nextPlayer() {
		currentPlayerIndex++;
		currentPlayerIndex %= 4;
		if (getKing(getCurrentPlayer()) == null) nextPlayer();
	}

	public ArrayList<Position> getAllValidMoves(Position from) {
		if (getKing(engine.board.getPlayer(from)) == null) return  new ArrayList<>();
		if (inOrder) {
			if (engine.board.getFigure(from).getPlayer() != getCurrentPlayer()) return new ArrayList<>();
		}

		ArrayList<Position> validMoves = engine.getAllValidMoves(from);

		if (engine.board.getFigure(from) instanceof King) {
			ArrayList<Position> invalidMoves = getInvalidKingMoves(from);

				validMoves.forEach(position -> position.add(from));

				validMoves.removeAll(invalidMoves);

			validMoves.forEach(position -> position.add(Position.mul(from, -1)));
		}

		return validMoves;
	}

	public ArrayList<Position> getInvalidKingMoves(Position from) {
		ArrayList<Position> invalid = new ArrayList<>();

		Player king = engine.board.getPlayer(from);

		ArrayList<Position> allFigures = engine.board.getAllPositions();
		ArrayList<Position> allAttacks = getAllAttacksExceptFromPlayer(king);

		ArrayList<Position> kingMoves = engine.getAllValidMoves(from);
		kingMoves.replaceAll(position1 -> Position.add(position1, from));

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
		if (getKing(engine.board.getPlayer(from)) == null) throw new InvalidMoveException("Player is already dead");
		if (inOrder) {
			if (engine.board.getFigure(from).getPlayer() != getCurrentPlayer()) throw new InvalidMoveException("Invalid Player selected");
		}

		engine.board.onRoundStart();
		engine.move(from ,to);
		engine.board.onRoundEnd();
		nextPlayer();
	}
}
