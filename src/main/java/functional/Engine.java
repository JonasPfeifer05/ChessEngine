package functional;

import functional.figure.Figure;
import util.exceptions.InvalidMoveException;
import util.Position;

import java.util.ArrayList;

public class Engine {
    private final Board board;

    public Engine(int players) {
        this.board = new Board(players);
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
                break;
            }
        }

        for (Position attackDirection : fromFigure.getAttackDirections()) {
            for (int i = 0; i < fromFigure.getMaxAttackDistance(); i++) {
                Position newPos = Position.add(from, Position.mul(attackDirection, i));

                Figure at = board.getFigure(newPos);

                if (at == null) {
                    continue;
                }

                if (at.getPlayer() == fromFigure.getPlayer()) break;
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
        if (toFigure != null && toFigure.getPlayer() == fromFigure.getPlayer()) throw new InvalidMoveException("Move from " + from + " to " + to + " is invalid!");

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
