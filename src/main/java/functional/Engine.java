package functional;

import functional.figure.Figure;
import util.exceptions.InvalidMoveException;
import util.Position;

import java.util.ArrayList;

public class Engine {
    public final Board board;

    public Engine(int players) {
        this.board = new Board(players);
    }

    public ArrayList<Position> getAllValidMoves(Position from) {
        if (!Board.inBound(from)) return new ArrayList<>();

        ArrayList<Position> validNormalMoves = getValidNormalMoves(from);

        Figure fromFigure = board.getFigure(from);
        if (fromFigure == null) return new ArrayList<>();

        validNormalMoves.addAll(fromFigure.getConditionalMoves(board, from));
        validNormalMoves.addAll(fromFigure.getConditionalAttacks(board, from));

        return validNormalMoves;
    }

    public ArrayList<Position> getValidNormalMoves(Position from) {
        if (!Board.inBound(from)) return new ArrayList<>();

        Figure fromFigure = board.getFigure(from);
        if (fromFigure == null) return new ArrayList<>();

        ArrayList<Position> validMoves = new ArrayList<>();

        for (Position moveDirection : fromFigure.getMoveDirections()) {
            for (int i = 1; i < fromFigure.getMaxMoveDistance()+1; i++) {
                Position newPos = Position.add(from, Position.mul(moveDirection, i));

                if (!Board.inBound(newPos)) break;

                Figure at = board.getFigure(newPos);

                if (at == null) {
                    validMoves.add(Position.mul(moveDirection, i));
                    continue;
                }
                break;
            }
        }

        for (Position attackDirection : fromFigure.getAttackDirections()) {
            for (int i = 1; i < fromFigure.getMaxAttackDistance()+1; i++) {
                Position newPos = Position.add(from, Position.mul(attackDirection, i));

                if (!Board.inBound(newPos)) break;

                Figure at = board.getFigure(newPos);

                if (at == null) {
                    continue;
                }

                if (at.getPlayer() == fromFigure.getPlayer()) break;
                validMoves.add(Position.mul(attackDirection, i));
                break;
            }
        }

        return validMoves;
    }

    public void move(Position from, Position to) throws InvalidMoveException, IndexOutOfBoundsException {
        if (!Board.inBound(to)) throw new IndexOutOfBoundsException("Position " + to + " is out of bound!");
        if (!Board.inBound(from)) throw new IndexOutOfBoundsException("Position " + from + " is out of bound!");

        Figure fromFigure = board.getFigure(from);

        Figure toFigure = board.getFigure(to);
        if (toFigure != null && toFigure.getPlayer() == fromFigure.getPlayer()) throw new InvalidMoveException("Move from " + from + " to " + to + " is invalid!");

        for (Position conditionalMove : fromFigure.getConditionalMoves(board, from)) {
            Position newPos = Position.add(from, conditionalMove);

            if (!Board.inBound(newPos)) break;

            if (board.getFigure(newPos) != null) continue;

            if (!newPos.equals(to)) continue;

            board.move(from, to);
            fromFigure.actionOnConditionalMove(board, from, conditionalMove);
            return;
        }

        for (Position conditionalAttack : fromFigure.getConditionalAttacks(board, from)) {
            Position newPos = Position.add(from, conditionalAttack);

            if (!Board.inBound(newPos)) break;

            if (board.getFigure(newPos) == null || board.getFigure(newPos).getPlayer() == fromFigure.getPlayer()) continue;

            if (!newPos.equals(to)) continue;

            board.move(from, to);
            fromFigure.actionOnConditionalAttack(board, from, conditionalAttack);
            return;
        }

        ArrayList<Position> validMoves = getValidNormalMoves(from);

        for (Position position : validMoves) {
            if (Position.add(position, from).equals(to)) {
                board.move(from, to);
                return;
            }
        }

        throw new InvalidMoveException("Move from " + from + " to " + to + " is invalid!");
    }
}
