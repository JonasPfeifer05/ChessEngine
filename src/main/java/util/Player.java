package util;

import java.awt.*;

/**
 * Created: 07.09.2022
 *
 * @author Jonas Pfeifer (jonas)
 */

public enum Player {
	PLAYER1(Color.RED), PLAYER2(Color.BLACK), PLAYER3(Color.BLUE), PLAYER4(Color.GREEN);

	public final Color color;

	Player(Color color) {
		this.color = color;
	}
}
