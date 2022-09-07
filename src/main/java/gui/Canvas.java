package gui;

import Functional.Board;
import util.Position;

import javax.swing.*;
import java.awt.*;

/**
 * Created: 07.09.2022
 *
 * @author Tobias Frischmann
 */

public class Canvas extends JPanel {
    private final Window window;

    private final Color BG_COLOR = Color.decode("#515870");
    private final Color DARK_COLOR = Color.decode("#262638");
    private final Color LIGHT_COLOR = Color.decode("#e8f3ff");

    public Canvas(Window window) {
        this.window = window;
        setPreferredSize(new Dimension(window.width, window.height));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //bg
        g2d.setColor(BG_COLOR);
        g2d.fillRect(0, 0, window.width, window.height);

        //grid
        int completeBoardSize = Board.FIELDS_PER_SIDE;
        float cellSize = window.height / (float) completeBoardSize;
        float xOffSet = (window.width - cellSize * completeBoardSize) / 2;

        g2d.setFont(new Font(Font.DIALOG, Font.PLAIN, 15));

        for (int x = 0; x < completeBoardSize; x++) {
            for (int y = 0; y < completeBoardSize; y++) {
                if (!Board.inBound(new Position(x, y))) continue;

                if ((x * completeBoardSize + y) % 2 == x % 2) {
                    g2d.setColor(LIGHT_COLOR);
                }else{
                    g2d.setColor(DARK_COLOR);
                }

                g2d.fillRect((int) (x * cellSize + xOffSet), (int) (y * cellSize), (int) cellSize, (int) cellSize);
            }
        }

    }
}
