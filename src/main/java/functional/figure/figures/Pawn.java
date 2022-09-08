package functional.figure.figures;

import functional.Board;
import functional.figure.PlayerDependentFigure;
import util.Asset;
import util.Player;
import util.Position;

import java.awt.image.BufferedImage;
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

    /*
    private static final ArrayList<Position> deletePositions = new ArrayList<>();
    private static final ArrayList<Integer> deleteCounter = new ArrayList<>();
     */

    static {
        moveDirectionsPlayer1.add(new Position(0,1));
        moveDirectionsPlayer4.add(new Position(-1,0));
        moveDirectionsPlayer2.add(new Position(0,-1));
        moveDirectionsPlayer3.add(new Position(1,0));

        moveDirectionsPlayer1.add(new Position(1,1));
        moveDirectionsPlayer1.add(new Position(-1,1));
        moveDirectionsPlayer4.add(new Position(-1,1));
        moveDirectionsPlayer4.add(new Position(-1,-1));
        moveDirectionsPlayer2.add(new Position(1,-1));
        moveDirectionsPlayer2.add(new Position(-1,-1));
        moveDirectionsPlayer3.add(new Position(1,1));
        moveDirectionsPlayer3.add(new Position(1,-1));
    }

    public Pawn(Player player) {
        super(player, moveDirectionsPlayer1, moveDirectionsPlayer2, moveDirectionsPlayer3, moveDirectionsPlayer4, attackDirectionsPlayer1, attackDirectionsPlayer2, attackDirectionsPlayer3, attackDirectionsPlayer4, 1, 1);
    }

    /*
    @Override
    public ArrayList<Position> getConditionalMoves(Position from) {
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
                if (from.x == 1) {
                    additional.add(new Position(2,0));
                }
            }
            case PLAYER4 -> {
                if (from.x == 12) {
                    additional.add(new Position(-2,0));
                }
            }
        }
        return additional;
    }

    @Override
    public void actionOnConditionalMove(Board board, Position from, Position move) {
        switch (getPlayer()) {
            case PLAYER1 -> {
                deletePositions.add(Position.add(from, moveDirectionsPlayer1.get(0)));
                board.set(Position.add(from, moveDirectionsPlayer1.get(0)), this);
            }
            case PLAYER2 -> {
                deletePositions.add(Position.add(from, moveDirectionsPlayer2.get(0)));
                board.set(Position.add(from, moveDirectionsPlayer2.get(0)), this);
            }
            case PLAYER3 -> {
                deletePositions.add(Position.add(from, moveDirectionsPlayer3.get(0)));
                board.set(Position.add(from, moveDirectionsPlayer3.get(0)), this);
            }
            case PLAYER4 -> {
                deletePositions.add(Position.add(from, moveDirectionsPlayer4.get(0)));
                board.set(Position.add(from, moveDirectionsPlayer4.get(0)), this);
            }
        }
    }

    @Override
    public void actionOnRoundStart(Board board) {
        for (int i = deleteCounter.size()-1; i >= 0; i--) {
            if (deleteCounter.get(i) <= 0) {
                deleteCounter.remove(i);
                if (board.getFigure(deletePositions.get(i)).equals(this)) board.set(deletePositions.get(i), null);
                deletePositions.remove(i);
            }
        }
    }

     */
}
