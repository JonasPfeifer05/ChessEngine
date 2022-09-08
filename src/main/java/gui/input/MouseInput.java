package gui.input;

import gui.Window;
import util.Position;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created: 08.09.2022
 *
 * @author Tobias Frischmann
 */

public class MouseInput implements MouseListener {

    private final Window window;

    public MouseInput(Window window) {
        this.window = window;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Position position = window.screenToWorldCords(e.getX(), e.getY());

        window.setSelectedPosition(position);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
