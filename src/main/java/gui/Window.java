package gui;

import functional.Board;
import functional.Engine;
import util.Position;

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
    private final ArrayList<Animation> animations = new ArrayList<>();

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

        return new Position(worldX, worldY);
    }

    public Position getSelectedPosition() {
        return selectedPosition;
    }

    public void setSelectedPosition(Position selectedPosition) {
        if (engine.board.getFigure(selectedPosition) == null) return;

        this.selectedPosition = selectedPosition;

        if (selectedPosition == null) {
            validMoves = null;
        } else {
            validMoves = engine.getAllValidMoves(selectedPosition);

            validMoves.replaceAll(position1 -> Position.add(position1, selectedPosition));
        }
    }

    public void manageClick(Position position) {

        if (!Board.inBound(position)) return;

        if (validMoves != null) {
            for (Position validMove : validMoves) {
                if (validMove.equals(position)) {

                    animations.add(new Animation(engine.board.getFigure(selectedPosition), selectedPosition, validMove));
                    engine.move(selectedPosition, validMove);

                    validMoves = null;
                    selectedPosition = null;

                    return;
                }
            }
        }

        setSelectedPosition(position);
    }

    public ArrayList<Position> getValidMoves() {
        return validMoves;
    }

    public void update() {
        ArrayList<Animation> animationsToRemove = new ArrayList<>();

        for (Animation animation : animations) {
            if (animation.update()) {
                animationsToRemove.add(animation);
            }
        }

        for (Animation animation : animationsToRemove) {
            animations.remove(animation);
        }
    }

    public ArrayList<Animation> getAnimations() {
        return animations;
    }
}
