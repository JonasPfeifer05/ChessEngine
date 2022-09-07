package functional.figure.figures;

import functional.figure.PlayerIndependentFigure;
import util.Player;
import util.Position;

import java.util.ArrayList;

public class Knight extends PlayerIndependentFigure {
    public static final ArrayList<Position> moveDirections = new ArrayList<>();

    static {
        int s = 1;
        int l = 2;
        moveDirections.add(new Position(s, l));
        moveDirections.add(new Position(l, s));

        moveDirections.add(new Position(-s, -l));
        moveDirections.add(new Position(-l, -s));

        moveDirections.add(new Position(-s, l));
        moveDirections.add(new Position(-l, s));

        moveDirections.add(new Position(s, -l));
        moveDirections.add(new Position(l, -s));

    }

    public Knight(Player player) {
        super(player, moveDirections, moveDirections, 1, 1);
    }
}
