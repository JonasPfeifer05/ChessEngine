package Functional.Figure;

import util.Position;

import java.util.ArrayList;

/**
 * Base Class for every Figure on the Field. Contains if the Figure is white and what Moves the Figure can do.
 */
public abstract class Figure {
    private final boolean white;

    public Figure(boolean white) {
        this.white = white;
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

    /*
     * Abstract Functions
     */
    public abstract ArrayList<Position> getMoveSet();
}