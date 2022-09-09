package functional.figure.figures;

import functional.Board;
import functional.figure.PlayerDependentFigure;
import functional.figure.special.KillLinked;
import util.Player;
import util.Position;

import java.util.ArrayList;

public class Pawn extends PlayerDependentFigure {
    public static final ArrayList<Position> moveDirectionsPlayer1 = new ArrayList<>();
    public static final ArrayList<Position> moveDirectionsPlayer2 = new ArrayList<>();
    public static final ArrayList<Position> moveDirectionsPlayer3 = new ArrayList<>();
    public static final ArrayList<Position> moveDirectionsPlayer4 = new ArrayList<>();

    public static final ArrayList<Position> attackDirectionsPlayer1 = new ArrayList<>();
    public static final ArrayList<Position> attackDirectionsPlayer2 = new ArrayList<>();
    public static final ArrayList<Position> attackDirectionsPlayer3 = new ArrayList<>();
    public static final ArrayList<Position> attackDirectionsPlayer4 = new ArrayList<>();

    static {
        moveDirectionsPlayer1.add(new Position(0,1));
        moveDirectionsPlayer4.add(new Position(1,0));
        moveDirectionsPlayer2.add(new Position(0,-1));
        moveDirectionsPlayer3.add(new Position(-1,0));

        attackDirectionsPlayer1.add(new Position(1,1));
        attackDirectionsPlayer1.add(new Position(-1,1));
        attackDirectionsPlayer4.add(new Position(1,1));
        attackDirectionsPlayer4.add(new Position(1,-1));
        attackDirectionsPlayer2.add(new Position(1,-1));
        attackDirectionsPlayer2.add(new Position(-1,-1));
        attackDirectionsPlayer3.add(new Position(-1,1));
        attackDirectionsPlayer3.add(new Position(-1,-1));
    }

    public Pawn(Player player) {
        super(player, moveDirectionsPlayer1, moveDirectionsPlayer2, moveDirectionsPlayer3, moveDirectionsPlayer4, attackDirectionsPlayer1, attackDirectionsPlayer2, attackDirectionsPlayer3, attackDirectionsPlayer4, 1, 1);
    }


    @Override
    public ArrayList<Position> getConditionalMoves(Board board, Position from) {
        ArrayList<Position> additional = new ArrayList<>();
        switch (this.getPlayer()) {
            case PLAYER1 -> {
                if (from.y == 1) {
                    additional.add(new Position(0,2));
                }
            }
            case PLAYER2 -> {
                if (from.y == 12) {
                    additional.add(new Position(0,-2));
                }
            }
            case PLAYER3 -> {
                if (from.x == 12) {
                    additional.add(new Position(-2,0));
                }
            }
            case PLAYER4 -> {
                if (from.x == 1) {
                    additional.add(new Position(2,0));
                }
            }
        }
        return additional;
    }

    @Override
    public void actionOnConditionalMove(Board board, Position from, Position move) {
        Position pawnPos = Position.add(from, move);
        switch (getPlayer()) {
            case PLAYER1 -> {
                Position linkPos = Position.add(from, moveDirectionsPlayer1.get(0));
                board.set(linkPos, new KillLinked(this.getPlayer(), linkPos, pawnPos));
            }
            case PLAYER2 -> {
                Position linkPos = Position.add(from, moveDirectionsPlayer2.get(0));
                board.set(linkPos, new KillLinked(this.getPlayer(), linkPos, pawnPos));
            }
            case PLAYER3 -> {
                Position linkPos = Position.add(from, moveDirectionsPlayer3.get(0));
                board.set(linkPos, new KillLinked(this.getPlayer(), linkPos, pawnPos));
            }
            case PLAYER4 -> {
                Position linkPos = Position.add(from, moveDirectionsPlayer4.get(0));
                board.set(linkPos, new KillLinked(this.getPlayer(), linkPos, pawnPos));
            }
        }
    }
}
