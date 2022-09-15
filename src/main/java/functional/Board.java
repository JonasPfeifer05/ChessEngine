package functional;

import functional.figure.Figure;
import functional.figure.PlayerDependentFigure;
import functional.figure.PlayerIndependentFigure;
import functional.figure.figures.*;
import util.DoubleSet;
import util.Player;
import util.Position;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Board {
	public static final int FIELDS_PER_SIDE = 14;

	public static final int FIELDS_PER_CORNER_SIDE = 3;

	public final ArrayList<DoubleSet<Position, Position>> moves = new ArrayList<>();

	public final ArrayList<Figure> killed_Player1 = new ArrayList<>();
	public final ArrayList<Figure> killed_Player2 = new ArrayList<>();
	public final ArrayList<Figure> killed_Player3 = new ArrayList<>();
	public final ArrayList<Figure> killed_Player4 = new ArrayList<>();

	private final Figure[][] boardSave;

	/*
	 * Access via [x][y]
	 */
	private final Figure[][] board;

	public Board(int players) {
		board = new Figure[FIELDS_PER_SIDE][FIELDS_PER_SIDE];
		boardSave = new Figure[FIELDS_PER_SIDE][FIELDS_PER_SIDE];

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

		for (int i = 0; i < FIELDS_PER_SIDE - 2 * FIELDS_PER_CORNER_SIDE; i++) {
			board[i + FIELDS_PER_CORNER_SIDE][1] = new Pawn(Player.PLAYER1);
			board[i + FIELDS_PER_CORNER_SIDE][12] = new Pawn(Player.PLAYER2);
			if (players >= 4) board[1][i + FIELDS_PER_CORNER_SIDE] = new Pawn(Player.PLAYER4);
			if (players >= 3) board[12][i + FIELDS_PER_CORNER_SIDE] = new Pawn(Player.PLAYER3);
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
		board[5 + FIELDS_PER_CORNER_SIDE][13] = new Bishop(Player.PLAYER2);
		board[6 + FIELDS_PER_CORNER_SIDE][13] = new Knight(Player.PLAYER2);
		board[7 + FIELDS_PER_CORNER_SIDE][13] = new Rook(Player.PLAYER2);
		board[4 + FIELDS_PER_CORNER_SIDE][13] = new King(Player.PLAYER2);


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

	public boolean checkClearance(boolean x, Position from, int range, boolean excludeFirst) {
		if (!inBound(from)) throw new IndexOutOfBoundsException("Position " + from + " is outside the field!");

		for (int i = 0; i < Math.abs(range); i++) {
			if (i == 0 && excludeFirst) continue;
			if (x) {
				if (figureAt(Position.add(from, i * Integer.signum(range), 0))) return false;
			} else {
				if (figureAt(Position.add(from, 0, i * Integer.signum(range)))) return false;
			}
		}

		return true;
	}

	public void save(String file) {
		BufferedWriter stream;
		try {
			stream = new BufferedWriter(new FileWriter(file, false));

			StringBuilder builder = new StringBuilder();

			for (DoubleSet<Position, Position> move : moves) {
				builder.append(String.format("%d;%d;%d;%d,", move.obj1.x, move.obj1.y, move.obj2.x, move.obj2.y));
			}

			builder.deleteCharAt(builder.length()-1);
			stream.write(builder.toString());
			stream.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		//For Creation figure.getClass().getConstructor(Player.class).newInstance(Player.PLAYER1);
	}

	public void load(String file) {
		File save = new File(file);

		try {
			BufferedReader stream = new BufferedReader(new FileReader(save));

			String[] moves = stream.readLine().split(",");

			for (String move : moves) {
				String[] cords = move.split(";");

				int x1 = Integer.parseInt(cords[0]);
				int y1 = Integer.parseInt(cords[1]);
				int x2 = Integer.parseInt(cords[2]);
				int y2 = Integer.parseInt(cords[3]);

				move(new Position(x1, y1), new Position(x2, y2));
			}

			stream.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	public void set(Position position, Figure figure) throws IndexOutOfBoundsException {
		if (!inBound(position)) throw new IndexOutOfBoundsException("Accessed Figure is out of the PlayBoard!");

		board[position.x][position.y] = figure;
	}

	public ArrayList<Figure> getKilled(Player player) {
		switch (player) {
			case PLAYER1 -> {
				return killed_Player1;
			}
			case PLAYER2 -> {
				return killed_Player2;
			}
			case PLAYER3 -> {
				return killed_Player3;
			}
			case PLAYER4 -> {
				return killed_Player4;
			}
		}
		throw new IllegalArgumentException("Invalid player");
	}

	public void move(Position from, Position to) {
		if (getFigure(to) != null) {
			Figure fig = getFigure(to);
			fig.kill(this);
			switch (fig.getPlayer()) {

				case PLAYER1 -> {
					killed_Player1.add(fig);
				}
				case PLAYER2 -> {
					killed_Player2.add(fig);
				}
				case PLAYER3 -> {
					killed_Player3.add(fig);
				}
				case PLAYER4 -> {
					killed_Player4.add(fig);
				}
			}

		}
		set(to, getFigure(from));
		set(from, null);
		moves.add(new DoubleSet<>(from, to));
	}

	public void saveArr() {
		for (int i = 0; i < board.length; i++) {
			System.arraycopy(board[i], 0, boardSave[i], 0, board[i].length);
		}
	}

	public void loadArr() {
		for (int i = 0; i < boardSave.length; i++) {
			System.arraycopy(boardSave[i], 0, board[i], 0, boardSave[i].length);
		}
	}

	public ArrayList<Figure> getAllFigures() {
		ArrayList<Figure> figs = new ArrayList<>();

		for (Figure[] figures : board) {
			Collections.addAll(figs, figures);
		}

		return figs;
	}

	public ArrayList<Position> getAllPositions() {
		ArrayList<Position> positions = new ArrayList<>();

		for (int i = 0; i < board.length; i++) {
			for (int i1 = 0; i1 < board[i].length; i1++) {
				if (board[i][i1] == null) continue;

				positions.add(new Position(i, i1));
			}
		}

		return positions;
	}

	public Figure getFigure(Position position) throws IndexOutOfBoundsException {
		if (!inBound(position)) throw new IndexOutOfBoundsException("Accessed Figure is out of the PlayBoard!");

		return board[position.x][position.y];
	}

	public Player getPlayer(Position from) throws IndexOutOfBoundsException {
		if (!inBound(from)) throw new IndexOutOfBoundsException("Accessed Figure is out of the PlayBoard!");

		return board[from.x][from.y].getPlayer();
	}

	public boolean figureAt(Position from) {
		if (getFigure(from) == null) return false;

		return getFigure(from) instanceof PlayerDependentFigure || getFigure(from) instanceof PlayerIndependentFigure;
	}

	public void onRoundStart() {
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[x].length; y++) {

				Position pos = new Position(x, y);
				if (!inBound(pos)) continue;
				Figure figure = getFigure(pos);

				if (figure == null) continue;
				figure.actionOnRoundStart(this, pos);
			}
		}
	}

	public void onRoundEnd() {
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[x].length; y++) {
				Position pos = new Position(x, y);
				if (!inBound(pos)) continue;
				Figure figure = getFigure(pos);

				if (figure == null) continue;
				figure.actionOnRoundEnd(this, pos);
			}
		}
	}
}
