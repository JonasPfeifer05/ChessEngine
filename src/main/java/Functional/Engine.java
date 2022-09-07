package Functional;

import Functional.Figure.Figure;
import util.Exceptions.InvalidMoveException;
import util.Position;

import java.util.ArrayList;

public class Engine {
    private final Board board;

    public Engine() {
        this.board = new Board();
    }

    public ArrayList<Position> getValidMoves(Position from) throws InvalidMoveException {
        if (!Board.inBound(from)) throw new IndexOutOfBoundsException("Position " + from + " is out of bound!");

        Figure fromFigure = board.getFigure(from);
        if (fromFigure == null) throw new InvalidMoveException("There is no Figure at " + from + "!");

        ArrayList<Position> validMoves = new ArrayList<>();

        for (Position moveDirection : fromFigure.getMoveDirections()) {
            for (int i = 0; i < fromFigure.getMaxMoveDistance(); i++) {
                Position newPos = Position.add(from, Position.mul(moveDirection, i));

                Figure at = board.getFigure(newPos);

                if (at == null) {
                    validMoves.add(newPos);
                    continue;
                }

                if (at.isWhite() == fromFigure.isWhite()) break;

                validMoves.add(newPos);

                break;
            }
        }

        return validMoves;
    }

    public void move(Position from, Position to) throws InvalidMoveException, IndexOutOfBoundsException {
        if (!Board.inBound(to)) throw new IndexOutOfBoundsException("Position " + to + " is out of bound!");

        Figure fromFigure = board.getFigure(from);

        Figure toFigure = board.getFigure(to);
        if (toFigure.isWhite() == fromFigure.isWhite()) throw new InvalidMoveException("Move from " + from + " to " + to + " is invalid!");

        ArrayList<Position> validMoves = getValidMoves(from);

        for (Position position : validMoves) {
            if (Position.add(position, from).equals(to)) {
                board.move(from, to);
                return;
            }
        }

        throw new InvalidMoveException("Move from " + from + " to " + to + " is invalid!");
    }
}
