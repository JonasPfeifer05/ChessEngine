package functional;

import functional.figure.Figure;
import functional.figure.figures.*;
import util.Player;
import util.Position;

public class Board {
	public static final int FIELDS_PER_SIDE = 14;

	public static final int FIELDS_PER_CORNER_SIDE = 3;

	/*
	 * Access via [x][y]
	 */
	private final Figure[][] board;

	public Board(int players) {
		board = new Figure[FIELDS_PER_SIDE][FIELDS_PER_SIDE];

		setUp(players);
	}

	/*
	Static Functions
	 */
	public static boolean inBound(Position position) {
		if (!(position.x >= 0 && position.x < FIELDS_PER_SIDE && position.y >= 0 && position.y < FIELDS_PER_SIDE))
			return false;

		if (position.y < FIELDS_PER_CORNER_SIDE || position.y >= FIELDS_PER_SIDE - FIELDS_PER_CORNER_SIDE) {
			return position.x >= FIELDS_PER_CORNER_SIDE && position.x < FIELDS_PER_SIDE - FIELDS_PER_CORNER_SIDE;
		}
		return true;
	}

	/*
	Member Functions
	*/
	private void setUp(int players) {
		if (players < 2) throw new IllegalArgumentException("Cannot play with less than 2 player");

		for (int i = 0; i < FIELDS_PER_SIDE-2*FIELDS_PER_CORNER_SIDE; i++) {
			board[i + FIELDS_PER_CORNER_SIDE][1] = new Pawn(Player.PLAYER1);
			board[i + FIELDS_PER_CORNER_SIDE][12] = new Pawn(Player.PLAYER2);
			board[1][i + FIELDS_PER_CORNER_SIDE] = new Pawn(Player.PLAYER4);
			board[12][i + FIELDS_PER_CORNER_SIDE] = new Pawn(Player.PLAYER3);
		}

		board[FIELDS_PER_CORNER_SIDE][0] = new Rook(Player.PLAYER1);
		board[1 + FIELDS_PER_CORNER_SIDE][0] = new Knight(Player.PLAYER1);
		board[2 + FIELDS_PER_CORNER_SIDE][0] = new Bishop(Player.PLAYER1);
		board[3 + FIELDS_PER_CORNER_SIDE][0] = new Queen(Player.PLAYER1);
		board[4 + FIELDS_PER_CORNER_SIDE][0] = new King(Player.PLAYER1);
		board[5 + FIELDS_PER_CORNER_SIDE][0] = new Bishop(Player.PLAYER1);
		board[6 + FIELDS_PER_CORNER_SIDE][0] = new Knight(Player.PLAYER1);
		board[7 + FIELDS_PER_CORNER_SIDE][0] = new Rook(Player.PLAYER1);

		board[FIELDS_PER_CORNER_SIDE][13] = new Rook(Player.PLAYER2);
		board[1 + FIELDS_PER_CORNER_SIDE][13] = new Knight(Player.PLAYER2);
		board[2 + FIELDS_PER_CORNER_SIDE][13] = new Bishop(Player.PLAYER2);
		board[3 + FIELDS_PER_CORNER_SIDE][13] = new Queen(Player.PLAYER2);
		board[4 + FIELDS_PER_CORNER_SIDE][13] = new King(Player.PLAYER2);
		board[5 + FIELDS_PER_CORNER_SIDE][13] = new Bishop(Player.PLAYER2);
		board[6 + FIELDS_PER_CORNER_SIDE][13] = new Knight(Player.PLAYER2);
		board[7 + FIELDS_PER_CORNER_SIDE][13] = new Rook(Player.PLAYER2);

		if (players >= 4) {
			board[0][FIELDS_PER_CORNER_SIDE] = new Rook(Player.PLAYER4);
			board[0][1 + FIELDS_PER_CORNER_SIDE] = new Knight(Player.PLAYER4);
			board[0][2 + FIELDS_PER_CORNER_SIDE] = new Bishop(Player.PLAYER4);
			board[0][3 + FIELDS_PER_CORNER_SIDE] = new Queen(Player.PLAYER4);
			board[0][4 + FIELDS_PER_CORNER_SIDE] = new King(Player.PLAYER4);
			board[0][5 + FIELDS_PER_CORNER_SIDE] = new Bishop(Player.PLAYER4);
			board[0][6 + FIELDS_PER_CORNER_SIDE] = new Knight(Player.PLAYER4);
			board[0][7 + FIELDS_PER_CORNER_SIDE] = new Rook(Player.PLAYER4);
		}

		if (players >= 3) {
			board[13][FIELDS_PER_CORNER_SIDE] = new Rook(Player.PLAYER3);
			board[13][1 + FIELDS_PER_CORNER_SIDE] = new Knight(Player.PLAYER3);
			board[13][2 + FIELDS_PER_CORNER_SIDE] = new Bishop(Player.PLAYER3);
			board[13][3 + FIELDS_PER_CORNER_SIDE] = new Queen(Player.PLAYER3);
			board[13][4 + FIELDS_PER_CORNER_SIDE] = new King(Player.PLAYER3);
			board[13][5 + FIELDS_PER_CORNER_SIDE] = new Bishop(Player.PLAYER3);
			board[13][6 + FIELDS_PER_CORNER_SIDE] = new Knight(Player.PLAYER3);
			board[13][7 + FIELDS_PER_CORNER_SIDE] = new Rook(Player.PLAYER3);
		}
	}

	public void set(Position position, Figure figure) throws IndexOutOfBoundsException {
		if (!inBound(position)) throw new IndexOutOfBoundsException("Accessed Figure is out of the PlayBoard!");

		board[position.x][position.y] = figure;
	}

	public void move(Position from, Position to) {
		if (getFigure(to) != null) getFigure(to).kill(this);
		set(to, getFigure(from));
		set(from, null);
	}

	public Figure getFigure(Position position) throws IndexOutOfBoundsException {
		if (!inBound(position)) throw new IndexOutOfBoundsException("Accessed Figure is out of the PlayBoard!");

		return board[position.x][position.y];
	}

	public Player getPlayer(Position from) {
		return board[from.x][from.y].getPlayer();
	}
}
