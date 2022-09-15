package gui;

import functional.Board;
import functional.Game;
import functional.figure.Figure;
import util.Position;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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
    public final Game game;

    private int width;
    private int height;

    private float cellSize;
    private float xOffSet;
    private float yOffSet;

    private final ArrayList<Animation> animations = new ArrayList<>();

    private Position selectedPosition;
    private ArrayList<Position> validMoves;

    public Window(int width, int height, Game engine) {
        this.width = width;
        this.height = height;

        computeValues();

        this.game = engine;

        instantiate();
        repaintClock();
    }

    private void computeValues() {
        cellSize = Math.min(height / (float) (Board.FIELDS_PER_SIDE + 5), width / (float) (Board.FIELDS_PER_SIDE + 4));
        xOffSet = (width - cellSize * Board.FIELDS_PER_SIDE) / 4;
        yOffSet = (height - cellSize * Board.FIELDS_PER_SIDE) / 5;
    }

    private void instantiate() {
        JFrame jFrame = new JFrame();

        jFrame.setTitle("Chess");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        canvas = new Canvas(this);
        jFrame.add(canvas);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);

        canvas.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                width = e.getComponent().getWidth();
                height = e.getComponent().getHeight();

                computeValues();
            }
        });

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
        int worldX = (int) ((x - xOffSet * 2) / cellSize);
        int worldY = (int) ((y - yOffSet * 3) / cellSize);

        return new Position(worldX, worldY);
    }

    public int toScreenX(float x) {
        return (int) (x * cellSize + xOffSet * 2);
    }

    public int toScreenY(float y) {
        return (int) (y * cellSize + yOffSet * 3);
    }

    public Position getSelectedPosition() {
        return selectedPosition;
    }

    public void setSelectedPosition(Position selectedPosition) {
        Figure figure = game.engine.board.getFigure(selectedPosition);
        if (figure == null || (figure.getPlayer() != game.getCurrentPlayer() && game.inOrder)) return;

        this.selectedPosition = selectedPosition;

        if (selectedPosition == null) {
            validMoves = null;
        } else {
            validMoves = game.getAllValidMoves(selectedPosition);

            validMoves.replaceAll(position1 -> Position.add(position1, selectedPosition));
        }
    }

    public void manageClick(Position position) {

        if (!Board.inBound(position)) return;

        if (validMoves != null) {
            for (Position validMove : validMoves) {
                if (validMove.equals(position)) {

                    animations.add(new Animation(game.engine.board.getFigure(selectedPosition), selectedPosition, validMove));
                    game.move(selectedPosition, validMove);

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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getCellSize() {
        return cellSize;
    }
}
