package Functional.Figure;

import util.Player;
import util.Position;

import java.util.ArrayList;

/**
 * A Class for Figures whose MoveSet is independent of the Color of the Figure.
 */
public abstract class PlayerIndependentFigure extends Figure {
    private final ArrayList<Position> moveDirections;
    private final ArrayList<Position> attackDirections;

    public PlayerIndependentFigure(Player player, ArrayList<Position> moveDirections, ArrayList<Position> attackDirections, int maxMoveDistance, int maxAttackDistance) {
        super(player, maxMoveDistance, maxAttackDistance);
        this.moveDirections = moveDirections;
        this.attackDirections = attackDirections;
    }

    @Override
    public ArrayList<Position> getMoveDirections() {
        return moveDirections;
    }

    @Override
    public ArrayList<Position> getAttackDirections() {
        return attackDirections;
    }
}