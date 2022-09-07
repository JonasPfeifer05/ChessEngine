package Functional.Figure.Figures;

import Functional.Figure.PlayerDependentFigure;
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
}
