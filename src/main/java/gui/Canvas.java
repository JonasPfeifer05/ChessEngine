package gui;

import functional.Board;
import functional.figure.Figure;
import functional.figure.figures.*;
import util.Asset;
import util.Position;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
                } else {
                    g2d.setColor(DARK_COLOR);
                }

                g2d.fillRect((int) (x * cellSize + xOffSet), (int) (y * cellSize), (int) cellSize, (int) cellSize);

                Figure figure = window.engine.board.getFigure(new Position(x, y));

                if (figure != null) {
                    g2d.setColor(figure.getPlayer().color);
                    g2d.drawImage(Asset.getSprite(figure.getPlayer(), getFigureId(figure)), (int) (x * cellSize + xOffSet), (int) (y * cellSize), (int) cellSize, (int) cellSize, this);
                }
            }
        }
    }

    private int getFigureId(Figure figure) {
        if (figure instanceof King) {
            return 0;
        } else if (figure instanceof Queen) {
            return 1;
        } else if (figure instanceof Bishop) {
            return 2;
        } else if (figure instanceof Knight) {
            return 3;
        } else if (figure instanceof Rook) {
            return 4;
        } else return 5;
    }
}
