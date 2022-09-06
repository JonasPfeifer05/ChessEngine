package Functional;

import Functional.Figures.Figure;
import util.Exceptions.InvalidMoveException;
import util.Position;

public class Engine {
    private final Board board;

    public Engine() {
        this.board = new Board();
    }

    public void move(Position from, Position to) throws InvalidMoveException {
        Figure fromFigure = board.getFigure(from);
        if (fromFigure == null) throw new InvalidMoveException("Move from " + from + " to " + to + " is invalid!");

        Figure toFigure = board.getFigure(to);
        if (toFigure.isWhite() == fromFigure.isWhite()) throw new InvalidMoveException("Move from " + from + " to " + to + " is invalid!");

        for (Position position : fromFigure.getMoveSet()) {
            if (Position.add(position, fromFigure.getPosition()).equals(to)) {
                board.move(from, to);
                return;
            }
        }

        throw new InvalidMoveException("Move from " + from + " to " + to + " is invalid!");
    }
}
