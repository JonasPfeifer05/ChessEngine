package Functional;

import Functional.Figure.Figure;
import util.Exceptions.InvalidMoveException;
import util.Position;

public class Engine {
    private final Board board;

    public Engine() {
        this.board = new Board();
    }

    public void move(Position from, Position to) throws InvalidMoveException, IndexOutOfBoundsException {
        if (!Board.inBound(from)) throw new IndexOutOfBoundsException("Position " + from + " is out of bound!");
        if (!Board.inBound(to)) throw new IndexOutOfBoundsException("Position " + to + " is out of bound!");

        Figure fromFigure = board.getFigure(from);
        if (fromFigure == null) throw new InvalidMoveException("Move from " + from + " to " + to + " is invalid!");

        Figure toFigure = board.getFigure(to);
        if (toFigure.isWhite() == fromFigure.isWhite()) throw new InvalidMoveException("Move from " + from + " to " + to + " is invalid!");

        for (Position position : fromFigure.getMoveDirections()) {
            if (Position.add(position, from).equals(to)) {
                board.move(from, to);
                return;
            }
        }

        throw new InvalidMoveException("Move from " + from + " to " + to + " is invalid!");
    }
}
