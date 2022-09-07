package Functional.Figure;

import util.Position;

import java.util.ArrayList;

/**
 * Base Class for every Figure on the Field. Contains if the Figure is white and what Moves the Figure can do.
 */
public abstract class Figure {
    private final boolean white;
    private final int maxMoveDistance;

    public Figure(boolean white, int maxMoveDistance) {
        this.white = white;
        this.maxMoveDistance = maxMoveDistance;
    }

    /*
     * Static Functions
     */


    /*
     * Member Functions
     */
    public boolean isWhite() {
        return white;
    }

    public int getMaxMoveDistance() {
        return maxMoveDistance;
    }

    /*
     * Abstract Functions
     */
    public abstract ArrayList<Position> getMoveDirections();
}