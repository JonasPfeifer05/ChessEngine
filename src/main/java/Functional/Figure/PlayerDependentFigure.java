package Functional.Figure;

import util.Player;
import util.Position;

import java.util.ArrayList;

/**
 * A Class for Figures whose MoveSet varies depending on the Color of the Figure.
 */
public abstract class PlayerDependentFigure extends Figure {
    //TODO
    private final ArrayList<Position> moveDirectionsPlayer1;
    private final ArrayList<Position> moveDirectionsPlayer2;
    private final ArrayList<Position> moveDirectionsPlayer3;
    private final ArrayList<Position> moveDirectionsPlayer4;

    private final ArrayList<Position> attackDirectionsPlayer1;
    private final ArrayList<Position> attackDirectionsPlayer2;
    private final ArrayList<Position> attackDirectionsPlayer3;
    private final ArrayList<Position> attackDirectionsPlayer4;

    public PlayerDependentFigure(Player player, ArrayList<Position> moveDirectionsPlayer1, ArrayList<Position> moveDirectionsPlayer2, ArrayList<Position> moveDirectionsPlayer3,  ArrayList<Position> moveDirectionsPlayer4, ArrayList<Position> attackDirectionsPlayer1, ArrayList<Position> attackDirectionsPlayer2, ArrayList<Position> attackDirectionsPlayer3,  ArrayList<Position> attackDirectionsPlayer4, int maxMoveDistance, int maxAttackDistance) {
        super(player, maxMoveDistance, maxAttackDistance);
        this.moveDirectionsPlayer1 = moveDirectionsPlayer1;
        this.moveDirectionsPlayer2 = moveDirectionsPlayer2;
        this.moveDirectionsPlayer3 = moveDirectionsPlayer3;
        this.moveDirectionsPlayer4 = moveDirectionsPlayer4;

        this.attackDirectionsPlayer1 = attackDirectionsPlayer1;
        this.attackDirectionsPlayer2 = attackDirectionsPlayer2;
        this.attackDirectionsPlayer3 = attackDirectionsPlayer3;
        this.attackDirectionsPlayer4 = attackDirectionsPlayer4;
    }

    /**
     * @return MoveSet depending on the Color of the Figure
     */
    @Override
    public ArrayList<Position> getMoveDirections() {
        return switch (getPlayer()) {
            case PLAYER1 -> moveDirectionsPlayer1;
            case PLAYER2 -> moveDirectionsPlayer2;
            case PLAYER3 -> moveDirectionsPlayer3;
            case PLAYER4 -> moveDirectionsPlayer4;
        };
    }

    @Override
    public ArrayList<Position> getAttackDirections() {
        return switch (getPlayer()) {
            case PLAYER1 -> attackDirectionsPlayer1;
            case PLAYER2 -> attackDirectionsPlayer2;
            case PLAYER3 -> attackDirectionsPlayer3;
            case PLAYER4 -> attackDirectionsPlayer4;
        };
    }
}
