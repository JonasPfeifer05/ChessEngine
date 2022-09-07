package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created: 07.09.2022
 *
 * @author Tobias Frischmann
 */

public class Canvas extends JPanel {
    private final Window window;

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
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, window.width, window.height);
    }
}
