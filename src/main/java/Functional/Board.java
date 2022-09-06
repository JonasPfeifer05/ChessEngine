package Functional;

import Functional.Figures.Figure;
import util.Exceptions.InvalidMoveException;
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
        //ToDo
        throw new RuntimeException("SetUp needs to be implemented!");
    }

    public void move(Position from, Position to) throws InvalidMoveException {
        //ToDo
        throw new RuntimeException("SetUp needs to be implemented!");
        //throw new InvalidMoveException("Move from " + from + " to " + to + " is invalid!");
    }

    public Figure getFigure(Position position) throws IndexOutOfBoundsException {
        if (!inBound(position)) throw new IndexOutOfBoundsException("Accessed Figure is out of the PlayBoard!");

        return board[position.x][position.y];
    }

    public boolean isFigureWhite(Position position) {
        return getFigure(position).isWhite();
    }
}
