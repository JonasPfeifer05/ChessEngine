package Functional;

import Functional.Figure.Figure;
import Functional.Figure.Figures.*;
import util.Position;

public class Board {
    public static final int FIELDS_PER_SIDE = 8;

    /*
     * Access via [x][y]
     */
    private final Figure[][] board;

    public Board() {
        board = new Figure[FIELDS_PER_SIDE][FIELDS_PER_SIDE];

        setUp();
    }

    /*
    Static Functions
     */
    public static boolean inBound(Position position) {
        return position.x >= 0 && position.x < FIELDS_PER_SIDE && position.y >= 0 && position.y < FIELDS_PER_SIDE;
    }

    /*
    Member Functions
    */
    private void setUp() {
        for (int i = 0; i < FIELDS_PER_SIDE; i++) {
            board[i][1] = new Pawn(true);
            board[i][6] = new Pawn(false);
        }

        board[0][0] = new Rook(true);
        board[1][0] = new Knight(true);
        board[2][0] = new Bishop(true);
        board[3][0] = new Queen(true);
        board[4][0] = new King(true);
        board[5][0] = new Bishop(true);
        board[6][0] = new Knight(true);
        board[7][0] = new Rook(true);

        board[0][7] = new Rook(false);
        board[1][7] = new Knight(false);
        board[2][7] = new Bishop(false);
        board[3][7] = new Queen(false);
        board[4][7] = new King(false);
        board[5][7] = new Bishop(false);
        board[6][7] = new Knight(false);
        board[7][7] = new Rook(false);
    }

    public void set(Position position, Figure figure) throws IndexOutOfBoundsException {
        if (!inBound(position)) throw new IndexOutOfBoundsException("Accessed Figure is out of the PlayBoard!");

        board[position.x][position.y] = figure;
    }

    public void move(Position from, Position to) {
        set(to, getFigure(from));
        set(from, null);
    }

    public Figure getFigure(Position position) throws IndexOutOfBoundsException {
        if (!inBound(position)) throw new IndexOutOfBoundsException("Accessed Figure is out of the PlayBoard!");

        return board[position.x][position.y];
    }

    public boolean isFigureWhite(Position position) {
        return getFigure(position).isWhite();
    }
}
