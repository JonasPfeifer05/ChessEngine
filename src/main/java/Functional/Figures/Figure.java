package Functional.Figures;

import util.Position;

import java.util.ArrayList;

/**
 * Base Class for every Figure on the Field. Contains if the Figure is white, the Position of the Figure and what Moves the Figure can do.
 */
public abstract class Figure {
    private final boolean white;
    private final Position position;

    public Figure(boolean white, Position position) {
        this.white = white;
        this.position = position;
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

    public Position getPosition() {
        return position;
    }

    /*
     * Abstract Functions
     */
    public abstract ArrayList<Position> getMoveSet();
}