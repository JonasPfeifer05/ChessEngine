package gui;

import functional.Board;
import functional.Engine;
import util.Position;
import util.exceptions.InvalidMoveException;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created: 07.09.2022
 *
 * @author Tobias Frischmann
 */

public class Window {
    private Canvas canvas;
    public final Engine engine;

    public final int width;
    public final int height;

    public final float cellSize;
    public final float xOffSet;

    private Position selectedPosition;
    private ArrayList<Position> validMoves;

    public Window(int width, int height, Engine engine) {
        this.width = width;
        this.height = height;

        cellSize = height / (float) Board.FIELDS_PER_SIDE;
        xOffSet = (width - cellSize * Board.FIELDS_PER_SIDE) / 2;

        this.engine = engine;

        instantiate();
        repaintClock();
    }

    private void instantiate() {
        JFrame jFrame = new JFrame();

        jFrame.setTitle("Chess");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setResizable(false);

        canvas = new Canvas(this);
        jFrame.add(canvas);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);

        jFrame.setVisible(true);
    }

    private void repaintClock() {
        java.util.Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                canvas.repaint();
            }
        }, 0, 1000 / 60);
    }

    public Position screenToWorldCords(int x, int y) {
        int worldX = (int) ((x - xOffSet) / cellSize);
        int worldY = (int) (y / cellSize);

        if (worldX >= 0 && worldX < Board.FIELDS_PER_SIDE && worldY >= 0 && worldY < Board.FIELDS_PER_SIDE) {
            Position position = new Position(worldX, worldY);

            if (Board.inBound(position)) return position;
        }

        return null;
    }

    public Position getSelectedPosition() {
        return selectedPosition;
    }

    public void setSelectedPosition(Position selectedPosition) {
        this.selectedPosition = selectedPosition;

        if (selectedPosition == null) {
            validMoves = null;
        } else {
            try {
                validMoves = engine.getAllValidMoves(selectedPosition);
                System.out.println(validMoves.size());
            } catch (InvalidMoveException ignored) {
            }
        }
    }

    public ArrayList<Position> getValidMoves() {
        return validMoves;
    }
}
