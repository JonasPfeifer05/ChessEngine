package functional;

import functional.figure.Figure;
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
	public final int playerCount;

	public final boolean inOrder;

	private final boolean[] alive = new boolean[]{true, true, true, true};

	public Game(int playerCount) {
		this(playerCount, true);
	}

	public Game(int playerCount, boolean inOrder) {
		this.engine = new Engine(playerCount);
		this.inOrder = inOrder;
		this.playerCount = playerCount;
	}

	public Player getCurrentPlayer() {
		return Player.values()[currentPlayerIndex];
	}

	public boolean isAlive(Player player) {
		switch (player) {
			case PLAYER1 -> {
				return alive[0];
			}
			case PLAYER2 -> {
				return alive[1];
			}
			case PLAYER3 -> {
				return alive[2];
			}
			case PLAYER4 -> {
				return alive[3];
			}
		}
		return false;
	}

	public void setAlive(Player player, boolean value) {
		switch (player) {
			case PLAYER1 -> {
				alive[0] = value;
			}
			case PLAYER2 -> {
				alive[1] = value;
			}
			case PLAYER3 -> {
				alive[2] = value;
			}
			case PLAYER4 -> {
				alive[3] = value;
			}
		}
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
			if (!isAlive(engine.board.getPlayer(figure))) continue;
			if (engine.board.getFigure(figure).getPlayer() == player) continue;
			allAttacks.addAll(engine.getAllValidAttacksAbs(figure));
			System.out.print("");
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
		for (Position fig : engine.board.getAllPositions()) {
			if (engine.board.getPlayer(fig) != player) continue;
			allValidMoves.addAll(getAllValidMoves(fig));
		}
		allValidMoves.removeAll(getAllAttacksExceptFromPlayer(player));
		return allValidMoves.size() == 0;
	}

	public boolean lookStaleMate(Player player) {
		if (lookCheck(player)) return false;


		for (Position allPosition : engine.board.getAllPositions()) {
			if (engine.board.getPlayer(allPosition) != player) continue;

			if (getAllValidMoves(allPosition).size() > 0) {
				return false;
			}
		}

		return true;
	}

	private void nextPlayer() {
		currentPlayerIndex++;
		currentPlayerIndex %= playerCount;
		if (!isAlive(getCurrentPlayer())) nextPlayer();
	}

	public ArrayList<Position> getAllValidMoves(Position from) {
		if (!isAlive(engine.board.getPlayer(from))) return new ArrayList<>();
		if (inOrder) {
			if (engine.board.getFigure(from).getPlayer() != getCurrentPlayer()) return new ArrayList<>();
		}

		ArrayList<Position> validMoves = engine.getAllValidMoves(from);

		if (engine.board.getFigure(from) instanceof King) {
			ArrayList<Position> invalidMoves = getInvalidKingMoves(from);

			validMoves.forEach(position -> position.add(from));

			validMoves.removeAll(invalidMoves);

			validMoves.forEach(position -> position.add(Position.mul(from, -1)));
		} else {
			// FIXME: 14.09.2022
			ArrayList<Position> invalid = new ArrayList<>();

			Player player = engine.board.getPlayer(from);

			for (Position validMove : validMoves) {
				Position abs = Position.add(validMove, from);
				engine.board.saveArr();
				//Figure save = engine.board.getFigure(abs);
				engine.board.move(from, abs);

				if (lookCheck(player)) {
					invalid.add(validMove);
				}

				engine.board.loadArr();
				//engine.board.move(abs, from);
				//engine.board.set(abs, save);
			}

			validMoves.removeAll(invalid);
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

	public void killUnableToMovePlayer() {

		if (lookStaleMate(getCurrentPlayer()) || lookCheckMate(getCurrentPlayer())) setAlive(getCurrentPlayer(), false);
	}


	public void move(Position from, Position to) throws InvalidMoveException {
		if (!isAlive(engine.board.getPlayer(from))) throw new InvalidMoveException("Player is already dead");
		if (inOrder) {
			if (engine.board.getFigure(from).getPlayer() != getCurrentPlayer())
				throw new InvalidMoveException("Invalid Player selected");
		}
		/*
		if (lookCheck(getCurrentPlayer()) && !lookCheckMate(getCurrentPlayer())) {
			if (!(engine.board.getFigure(from) instanceof King)) throw new InvalidMoveException("Must move with King!");
		}
		 */

		Figure king = engine.board.getFigure(to);

		engine.board.onRoundStart();
		engine.move(from, to);
		engine.board.onRoundEnd();

		nextPlayer();
		System.out.println(getCurrentPlayer());
		killUnableToMovePlayer();

		Player current = getCurrentPlayer();
		System.out.println(String.format("""
				--------------------
				Check:     %s
				Checkmate: %s
				Stalemate: %s
				--------------------
				""", lookCheck(current) ? "true" : "false", lookCheckMate(current) ? "true" : "false", lookStaleMate(current) ? "true" : "false"));



		if (king instanceof King) {
			setAlive(king.getPlayer(), false);
		}
	}
}