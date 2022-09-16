package util;

import java.awt.*;

/**
 * Created: 07.09.2022
 *
 * @author Jonas Pfeifer (jonas)
 */

public enum Player {
    PLAYER1(Color.RED), PLAYER2(Color.ORANGE), PLAYER3(Color.BLUE), PLAYER4(Color.GREEN);

    public final Color color;
    public final Color deadColor;

    Player(Color color) {
        this.color = color;

        int avg = (color.getRed() + color.getGreen() + color.getBlue()) / 3;

        this.deadColor = new Color((color.getRed() + avg * 5) / 6, (color.getGreen() + avg * 5) / 6, (color.getBlue() + avg * 5) / 6, 255);
    }
}
