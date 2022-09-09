package gui;

import functional.Board;
import functional.figure.Figure;
import functional.figure.figures.*;
import functional.figure.special.KillLinked;
import gui.input.MouseInput;
import util.Asset;
import util.Position;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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

        addMouseListener(new MouseInput(window));
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
        g2d.setFont(new Font(Font.DIALOG, Font.PLAIN, 15));

        for (int x = 0; x < Board.FIELDS_PER_SIDE; x++) {
            for (int y = 0; y < Board.FIELDS_PER_SIDE; y++) {
                if (!Board.inBound(new Position(x, y))) continue;

                if ((x * Board.FIELDS_PER_SIDE + y) % 2 == x % 2) {
                    g2d.setColor(LIGHT_COLOR);
                } else {
                    g2d.setColor(DARK_COLOR);
                }

                g2d.fillRect((int) (x * window.cellSize + window.xOffSet), (int) (y * window.cellSize), (int) window.cellSize, (int) window.cellSize);
            }
        }
        //selected Tile
        Position selectedPosition = window.getSelectedPosition();

        if (selectedPosition != null) {
            g2d.setColor(Color.GRAY);
            g2d.fillOval((int) (selectedPosition.x * window.cellSize + window.xOffSet), (int) (selectedPosition.y * window.cellSize), (int) window.cellSize, (int) window.cellSize);

            ArrayList<Position> validMoves = window.getValidMoves();
            if (validMoves != null) {
                for (Position validMove : validMoves) {

                    if(window.engine.board.getFigure(validMove) == null){
                        g2d.setColor(Color.green);
                    }else{
                        g2d.setColor(Color.RED);
                    }

                    g2d.fillOval((int) (validMove.x * window.cellSize + window.xOffSet), (int) (validMove.y * window.cellSize), (int) window.cellSize, (int) window.cellSize);
                }
            }
        }

        //pieces
        for (int x = 0; x < Board.FIELDS_PER_SIDE; x++) {
            for (int y = 0; y < Board.FIELDS_PER_SIDE; y++) {
                if (!Board.inBound(new Position(x, y))) continue;
                Figure figure = window.engine.board.getFigure(new Position(x, y));

                if (figure != null && !(figure instanceof KillLinked)) {
                    g2d.setColor(figure.getPlayer().color);
                    g2d.drawImage(Asset.getSprite(figure.getPlayer(), getFigureId(figure)), (int) (x * window.cellSize + window.xOffSet), (int) (y * window.cellSize), (int) window.cellSize, (int) window.cellSize, this);
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
