package gui;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created: 07.09.2022
 *
 * @author Tobias Frischmann
 */

public class Window {
    private Canvas canvas;

    public final int width;
    public final int height;

    public Window(int width, int height) {
        this.width = width;
        this.height = height;

        instantiate();
        repaintClock();
    }

    private void instantiate() {
        JFrame jFrame = new JFrame();

        jFrame.setTitle("Chess");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

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
}
