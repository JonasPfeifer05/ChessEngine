package functional.figure;

import functional.Board;
import util.Player;
import util.Position;

import java.util.ArrayList;

/**
 * Base Class for every Figure on the Field. Contains if the Figure is white and what Moves the Figure can do.
 */
public abstract class Figure {
	private final Player player;
	private final int maxMoveDistance;
	private final int maxAttackDistance;

	public Figure(Player player, int maxMoveDistance, int maxAttackDistance) {
		this.player = player;
		this.maxMoveDistance = maxMoveDistance;
		this.maxAttackDistance = maxAttackDistance;
	}

	/*
	 * Static Functions
	 */


	/*
	 * Member Functions
	 */
	public Player getPlayer() {
		return player;
	}

	public int getMaxMoveDistance() {
		return maxMoveDistance;
	}

	public int getMaxAttackDistance() {
		return maxAttackDistance;
	}

	/*
	 * Abstract Functions
	 */
	public abstract ArrayList<Position> getMoveDirections();
	public ArrayList<Position> getConditionalMoves(Position from) {return new ArrayList<Position>();}
	public void actionOnConditionalMove(Board board, Position from, Position move) {}

	public abstract ArrayList<Position> getAttackDirections();
	public ArrayList<Position> getConditionalAttacks(Position from) {return new ArrayList<Position>();}
	public void actionOnConditionalAttack(Board board, Position from, Position attack) {}

	public void actionOnRoundStart(Board board) {}
}