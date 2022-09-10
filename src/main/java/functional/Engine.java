package functional;

import functional.figure.Figure;
import functional.figure.special.KillLinked;
import util.exceptions.InvalidMoveException;
import util.Position;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Engine {
    public final Board board;

    public Engine(int players) {
        this.board = new Board(players);
    }

    public ArrayList<Position> getAllValidMoves(Position from) {
        if (!Board.inBound(from)) return new ArrayList<>();

        ArrayList<Position> validNormalMoves = getValidNormalMoves(from);

        if (!board.figureAt(from)) return new ArrayList<>();
        Figure fromFigure = board.getFigure(from);

        validNormalMoves.addAll(fromFigure.getConditionalMoves(board, from));
        validNormalMoves.addAll(fromFigure.getConditionalAttacks(board, from));

        return validNormalMoves;
    }

    public ArrayList<Position> getValidNormalMoves(Position from) {
        if (!Board.inBound(from)) return new ArrayList<>();

        if (!board.figureAt(from)) return new ArrayList<>();
        Figure fromFigure = board.getFigure(from);

        ArrayList<Position> validMoves = new ArrayList<>();

        for (Position moveDirection : fromFigure.getMoveDirections()) {
            for (int i = 1; i < fromFigure.getMaxMoveDistance()+1; i++) {
                Position newPos = Position.add(from, Position.mul(moveDirection, i));

                if (!Board.inBound(newPos)) break;

                if (!board.figureAt(newPos)) {
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

                if (!board.figureAt(newPos)) {
                    if (at instanceof KillLinked) {
                        if (at.getPlayer() == fromFigure.getPlayer()) continue;
                        validMoves.add(Position.mul(attackDirection, i));
                    }
                    continue;
                }


                if (at.getPlayer() == fromFigure.getPlayer()) break;
                validMoves.add(Position.mul(attackDirection, i));
                break;
            }
        }

        return validMoves;
    }

    public ArrayList<Position> getAllValidAttacksAbsKing(Position from) {
        if (!Board.inBound(from)) return new ArrayList<>();

        if (!board.figureAt(from)) return new ArrayList<>();
        Figure fromFigure = board.getFigure(from);

        ArrayList<Position> validMoves = new ArrayList<>();

        for (Position attackDirection : fromFigure.getAttackDirections()) {
            for (int i = 1; i < fromFigure.getMaxAttackDistance()+1; i++) {
                Position newPos = Position.add(from, Position.mul(attackDirection, i));

                if (!Board.inBound(newPos)) break;

                Figure at = board.getFigure(newPos);

                if (!board.figureAt(newPos)) {
                    validMoves.add(Position.add(from, Position.mul(attackDirection, i)));
                    continue;
                }


                if (at.getPlayer() == fromFigure.getPlayer()) break;
                validMoves.add(Position.add(from, Position.mul(attackDirection, i)));
                break;
            }
        }

        for (Position conditionalAttack : fromFigure.getConditionalAttacks(board, from)) {
            validMoves.add(Position.add(from, conditionalAttack));
        }

        return validMoves;
    }

    public void move(Position from, Position to, ArrayList<Position> exclude) throws InvalidMoveException, IndexOutOfBoundsException {
        if (!Board.inBound(to)) throw new IndexOutOfBoundsException("Position " + to + " is out of bound!");
        if (!Board.inBound(from)) throw new IndexOutOfBoundsException("Position " + from + " is out of bound!");

        Figure fromFigure = board.getFigure(from);

        Figure toFigure = board.getFigure(to);
        if (board.figureAt(to) && toFigure.getPlayer() == fromFigure.getPlayer()) throw new InvalidMoveException("Move from " + from + " to " + to + " is invalid!");

        for (Position conditionalMove : fromFigure.getConditionalMoves(board, from)) {
            Position newPos = Position.add(from, conditionalMove);

            if (!Board.inBound(newPos)) continue;
            if (exclude.contains(newPos)) continue;

            if (board.figureAt(newPos)) continue;

            if (!newPos.equals(to)) continue;

            board.move(from, to);
            fromFigure.actionOnConditionalMove(board, from, conditionalMove);
            return;
        }

        for (Position conditionalAttack : fromFigure.getConditionalAttacks(board, from)) {
            Position newPos = Position.add(from, conditionalAttack);

            if (!Board.inBound(newPos)) continue;
            if (exclude.contains(newPos)) continue;


            if (!board.figureAt(newPos) || board.getFigure(newPos).getPlayer() == fromFigure.getPlayer()) continue;

            if (!newPos.equals(to)) continue;

            board.move(from, to);
            fromFigure.actionOnConditionalAttack(board, from, conditionalAttack);
            return;
        }

        ArrayList<Position> validMoves = getValidNormalMoves(from);

        for (Position position : validMoves) {
            if (Position.add(position, from).equals(to)) {
                if (exclude.contains(Position.add(position, from))) continue;
                board.move(from, to);
                return;
            }
        }

        throw new InvalidMoveException("Move from " + from + " to " + to + " is invalid!");
    }

    public void move(Position from, Position to) throws InvalidMoveException, IndexOutOfBoundsException {
        move(from, to, new ArrayList<>());
    }
}
